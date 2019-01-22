package frc.robot.extensions;

import java.util.Timer;
import java.util.TimerTask;

public abstract class SimplePID {
  double Kp, Ki, Kd;
  double setpoint = 0, tolerance = 0, nonZeroCycleCount = 0, lastError = 0;
  boolean isEnabled = false, isContinuous = false;
  public final static long rate = 5;
  Timer timer;

  public SimplePID(double p, double i, double d) {
    Kp = p;
    Ki = i;
    Kd = d;

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        runLoop();
      }
    }, rate, rate);
  }

  public final void runLoop() {
    if (!isEnabled)
      return;
    usePIDOutput(calculate(returnPIDInput()));
  }

  private final double calculate(double measured) {
    var error = setpoint - measured;
    if (error > 180) {
      error -= 360;
    } else if (error < -180) {
      error += 360;
    }
    if (Math.abs(error) <= tolerance) {
      nonZeroCycleCount = 0;
      return 0;
    }
    nonZeroCycleCount++;

    var p = Kp * error;
    var i = Ki * (error - nonZeroCycleCount);
    var d = Kd * (lastError - error / rate);

    lastError = error;

    return -(p + i - d);
  }

  public abstract double returnPIDInput();

  public abstract void usePIDOutput(double output);

  public void disable() {
    isEnabled = false;
  }

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
