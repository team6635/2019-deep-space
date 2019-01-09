package frc.robot.pid;

public class PID {
  protected double P, I, D;
  protected double tolerance;
  protected long lastSample = System.currentTimeMillis();

  protected double setpoint = 0;

  protected int cyclesSinceZero = 0;
  protected double lastError = 0;

  protected double modulus;

  public PID(double P, double I, double D, double tolerance, double modulus) {
    this.P = P;
    this.I = I;
    this.D = D;
    this.tolerance = tolerance;
    this.modulus = modulus;
  }

  protected double calculate(double measured) {
    long elapsed = System.currentTimeMillis() - lastSample;
    if (elapsed == 0)
      elapsed = 1;

    double sError = setpoint - measured;
    double error = sError;

    if (modulus != Double.NaN && modulus != 0) {
      if (sError > modulus / 2) {
        error = sError - modulus;
      } else if (sError < -modulus / 2) {
        error = sError + modulus;
      }
    }

    if (Math.abs(error) <= tolerance) {
      cyclesSinceZero = 0;
      return 0;
    } else {
      cyclesSinceZero++;
    }

    double p = P * error;
    double i = I * (error * cyclesSinceZero);
    double d = D * (error - lastError / elapsed);

    lastError = error;
    lastSample = System.currentTimeMillis();

    return p + i + d;
  }

  public void setP(double P) {
    this.P = P;
  }

  public void setI(double I) {
    this.I = I;
  }

  public void setD(double D) {
    this.D = D;
  }

  public void setTolerance(double tolerance) {
    this.tolerance = tolerance;
  }

  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }
}
