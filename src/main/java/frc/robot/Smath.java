package frc.robot;

public final class Smath {
  public static double normalizeAngle(double rawAngle) {
    rawAngle %= 360;
    if (rawAngle < 0) {
      return 360 + rawAngle;
    } else {
      return rawAngle;
    }
  }

  public static final class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
      set(x, y);
    }

    public double getAngle() {
      return Smath.normalizeAngle(Math.toDegrees(Math.atan2(y, x)) - 90);
    }

    double getMagnitude() {
      return Math.hypot(x, y);
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public void set(double x, double y) {
      setX(x);
      setY(y);
    }

    public void setX(double x) {
      this.x = x;
    }

    public void setY(double y) {
      this.y = y;
    }

    public void rotate(double angle) {
      angle = Smath.normalizeAngle(angle);
      double newAngle = Smath.normalizeAngle(angle + getAngle());
      // TODO: Convert the angle back to a vector and set the properties.
    }
  }
}
