package frc.robot.extensions;

public final class Smath {
  public static final double calculateAngleFromPoint(double x, double y) {
    return normalizeAngle(Math.atan2(x, y) * (180 / Math.PI));
  }

  public static final double normalizeAngle(double raw) {
    raw %= 360;
    return raw < 0 ? 360 + raw : raw;
  }

  public static final double absAdd(double... vals) {
    double sum = 0;
    for (double d : vals) {
      sum += Math.abs(d);
    }
    return sum;
  }

  public static final void normalizeSpeeds(double[] speeds, double limit) {
    double max = 0;
    for (double speed : speeds) {
      if (Math.abs(speed) > max) {
        max = Math.abs(speed);
      }
    }
    if (max > limit) {
      for (int i = 0; i < speeds.length; i++) {
        speeds[i] /= max;
      }
    }
  }

  public static final class Vector2 {
    private double x, y;

    public Vector2() {
      this(0, 0);
    }

    public Vector2(Vector2 instance) {
      this(instance.getX(), instance.getY());
    }

    public Vector2(double x, double y) {
      setX(x);
      setY(y);
    }

    public static Vector2 fromAngle(double angle, double magnitude) {
      angle *= (Math.PI / 180);
      final double x = Math.sin(angle) * magnitude;
      final double y = Math.cos(angle) * magnitude;
      return new Vector2(x, y);
    }

    // Multiplication

    public void mul(Vector2 other) {
      x *= other.getX();
      y *= other.getY();
    }

    public void mul(double value) {
      x *= value;
      y *= value;
    }

    public void mul(double otherX, double otherY) {
      x *= otherX;
      y *= otherY;
    }

    // Division

    public void div(Vector2 other) {
      x /= other.getX();
      y /= other.getY();
    }

    public void div(double value) {
      x /= value;
      y /= value;
    }

    public void div(double otherX, double otherY) {
      x /= otherX;
      y /= otherY;
    }

    // Addition

    public void add(Vector2 other) {
      x += other.getX();
      y += other.getY();
    }

    public void add(double value) {
      x += value;
      y += value;
    }

    public void add(double otherX, double otherY) {
      x += otherX;
      y += otherY;
    }

    public void rotate(double deltaAngle) {
      deltaAngle = normalizeAngle(deltaAngle);
      double currentAngle = getAngle();
      double modifiedAngle = normalizeAngle(currentAngle + deltaAngle);
      Vector2 temp = Vector2.fromAngle(modifiedAngle, getMagnitude());
      setX(temp.getX());
      setY(temp.getY());
    }

    public double getAngle() {
      return calculateAngleFromPoint(x, y);
    }

    public double getMagnitude() {
      return Math.hypot(x, y);
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public void setX(double value) {
      x = value;
    }

    public void setY(double value) {
      y = value;
    }
  }
}
