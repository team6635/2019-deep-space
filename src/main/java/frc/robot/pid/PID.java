package frc.robot.pid;

public class PID {
  protected double Kp, Ki, Kd;
  protected double tolerance;
  protected long lastSample = System.currentTimeMillis();

  protected double setpoint = 0;

  protected int cyclesSinceZero = 0;
  protected double lastError = 0;

  protected double modulus;

  public PID(double P, double I, double D, double tolerance, double modulus) {
    this.Kp = P;
    this.Ki = I;
    this.Kd = D;
    this.tolerance = tolerance;
    this.modulus = modulus;
  }

  protected double calculate(double measured) {
    long elapsed = System.currentTimeMillis() - lastSample;
    if (elapsed == 0)
      elapsed = 1;

    double error = setpoint - measured;

    if (modulus != Double.NaN && modulus != 0) {
      if (error > modulus / 2) {
        error -= modulus;
      } else if (error < -modulus / 2) {
        error += modulus;
      }
    }

    if (Math.abs(error) <= tolerance) {
      cyclesSinceZero = 0;
      return 0;
    } else {
      cyclesSinceZero++;
    }

    double p = Kp * error;
    double i = Ki * (error * cyclesSinceZero);
    double d = Kd * (lastError - error / elapsed);

    lastError = error;
    lastSample = System.currentTimeMillis();

    return -(p + i + d);
  }

  public void setP(double P) {
    this.Kp = P;
  }

  public void setI(double I) {
    this.Ki = I;
  }

  public void setD(double D) {
    this.Kd = D;
  }

  public void setTolerance(double tolerance) {
    this.tolerance = tolerance;
  }

  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }
}
