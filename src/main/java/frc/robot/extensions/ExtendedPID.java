package frc.robot.extensions;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This is a PID loop implementation. The use of this is to move an actuator to
 * a specific position in the most efficient way possible, and to stop in an
 * efficient way too so as not to overshoot.
 */
public abstract class ExtendedPID {
  double Kp;
  double Ki;
  double Kd;
  final Timer timer;
  final boolean continuous;
  final double modulus;
  final long rate;

  double setpoint = 0;
  double tolerance = 0;
  double nonZeroCycleCount = 0;
  double lastError = 0;
  boolean isEnabled = false;
  double outputLowerBound = -1;
  double outputHigherBound = 1;

  public ExtendedPID(double p, double i, double d) {
    this(p, i, d, true, 360, 5);
  }

  public ExtendedPID(double p, double i, double d, boolean continuous, double modulus, long rate) {
    Kp = p;
    Ki = i;
    Kd = d;
    this.continuous = continuous;
    this.modulus = modulus;
    this.rate = rate;

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        runLoop();
      }
    }, rate, rate);
  }

  public void setOutputBounds(double low, double high) {
    outputLowerBound = low;
    outputHigherBound = high;
  }

  public final void runLoop() {
    if (!isEnabled)
      return;
    if (continuous) {
      usePIDOutput(calculate(returnPIDInput() % modulus));
    } else {
      usePIDOutput(calculate(returnPIDInput()));
    }
  }

  private final double calculate(double measured) {
    var error = setpoint - measured;
    // If this is a continuous system, calculate the shortest path to the setpoint
    // to use as the error.
    if (continuous) {
      if (error > modulus / 2) {
        error -= modulus;
      } else if (error < -modulus / 2) {
        error += modulus;
      }
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

    var result = -(p + i - d);

    if (result > outputHigherBound) {
      return outputHigherBound;
    } else if (result < outputLowerBound) {
      return outputLowerBound;
    } else {
      return result;
    }
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
