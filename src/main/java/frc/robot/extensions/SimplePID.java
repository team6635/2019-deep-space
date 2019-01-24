package frc.robot.extensions;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is a simple PID loop implementation, used for our swerve drive code.
 * There are PID classes built-in to WPILib, but we were experiencing bugs when
 * we were using them, so we wrote our own.
 * 
 * <p>
 * The use of this is to move an actuator to a specific position in the most
 * efficient way possible, and to stop in an efficient way too so as not to
 * overshoot.
 * 
 * <p>
 * Please note that this class is specifially designed for use with continuous
 * PID loops, like those found on swerve drive wheels (pivot motor). This class
 * will not work as-is for non-continuous uses, such as arms and sliders.
 */
public abstract class SimplePID {
  /** The stored values for the tuning parameters. */
  double Kp, Ki, Kd;
  /** The target position for the PID loop to reach. */
  double setpoint = 0;
  /** The tolerable amount of error on any given cycle that will be ignored. */
  double tolerance = 0;
  /** The count of cycles that have passed since the error was tolerable. */
  double nonZeroCycleCount = 0;
  /** The error value encountered the last time the loop ran. */
  double lastError = 0;
  /** If false, the loop will not provide output. */
  boolean isEnabled = false;
  /** How often, in milliseconds, to call the loop using a Timer. */
  public final static long rate = 5;
  /**
   * The internal timer used for calling the loop on a fixed {@link SimplePID#rate
   * rate}.
   */
  Timer timer;

  /**
   * Constructs a new instance with the given tuning parameters.
   * 
   * @param p
   * @param i
   * @param d
   */
  public SimplePID(double p, double i, double d) {
    // Assign the parameters to the instance properties so they can be accessed
    // later.
    Kp = p;
    Ki = i;
    Kd = d;

    // Create the timer, and schedule a task to run runLoop every rate milliseconds.
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        runLoop();
      }
    }, rate, rate);
  }

  /**
   * Runs the PID loop once. Called by the internal Timer.
   */
  public final void runLoop() {
    if (!isEnabled)
      return;
    usePIDOutput(calculate(returnPIDInput()));
  }

  /**
   * Calculates the output to be sent to the output method.
   * 
   * @param measured the current input value, as measured from an encoder or other
   *                 sensor.
   * @return the output from the pid function.
   */
  private final double calculate(double measured) {
    var error = setpoint - measured;
    // Calculate the shortest path to the setpoint to use as the error.
    if (error > 180) {
      error -= 360;
    } else if (error < -180) {
      error += 360;
    }

    // If the error is tolerable, then we will reset the nonZeroCycleCount, and
    // return no output.
    if (Math.abs(error) <= tolerance) {
      nonZeroCycleCount = 0;
      return 0;
    }
    // Otherwise, increment it since we aren't yet at a tolerable error.
    nonZeroCycleCount++;

    var p = Kp * error;
    var i = Ki * error * nonZeroCycleCount;
    var d = Kd * (lastError - error / rate);

    lastError = error;

    return -(p + i - d);
  }

  /**
   * This method should return input from a feedback sensor, such as an encoder.
   * 
   * @return the input value for the PID function
   */
  public abstract double returnPIDInput();

  /**
   * This method should use the output from the PID function in order to affect
   * something that affects the feedback sensor that provides
   * {@link SimplePID#returnPIDInput returnPIDInput}.
   * 
   * @param output the output fed from the PID function.
   */
  public abstract void usePIDOutput(double output);

  /** Stops the internal Timer. */
  public void disable() {
    isEnabled = false;
  }

  /** Starts the internal Timer. */
  public void enable() {
    isEnabled = true;
  }

  /**
   * Sets the target setpoint.
   * 
   * @param setpoint the setpoint to set
   */
  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }

  /**
   * Sets the error tolerance.
   * 
   * @param tolerance the tolerance to set
   */
  public void setTolerance(double tolerance) {
    this.tolerance = tolerance;
  }
}
