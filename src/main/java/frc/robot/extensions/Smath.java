package frc.robot.extensions;

/**
 * Helpful math functions primarily used for swerve drive.
 */
public final class Smath {
  /**
   * Takes a cartesian point and returns the angle from (0, 0) to that point.
   * 
   * <p>
   * For example, the angle between (0, 0) and (1, 1) is 45 degrees.
   * 
   * @param x
   * @param y
   * @return the angle between (0, 0) and (x, y).
   */
  public static final double calculateAngleFromPoint(double x, double y) {
    return normalizeAngle(Math.atan2(x, y) * (180 / Math.PI));
  }

  /**
   * Normalizes an angle, effectively bringing it into the domain of many other
   * functions.
   * 
   * @param raw a raw value to resolve to a normal angle.
   * @return a normalized angle in the range [0, 360).
   */
  public static final double normalizeAngle(double raw) {
    raw %= 360;
    return raw < 0 ? 360 + raw : raw;
  }

  /**
   * Calculates the shortest error between two distances with a modulus.
   * 
   * @param posCurrent Current or starting position.
   * @param posTarget  Target position.
   * @param modulus    Modulus of the range.
   * @return The optimal error.
   */
  public static final double calculateError(double posCurrent, double posTarget, double modulus) {
    var error = posTarget - posCurrent;
    if (error > modulus / 2) {
      error -= modulus;
    } else if (error < -modulus / 2) {
      error += modulus;
    }

    return error % modulus;
  }

  /**
   * Calculates the sum of the absolute values of the inputs.
   * 
   * @param vals the values to sum absolutely.
   * @return the absolute sum of the values.
   */
  public static final double absAdd(double... vals) {
    double sum = 0;
    for (double d : vals) {
      sum += Math.abs(d);
    }
    return sum;
  }

  /**
   * Normalizes a set of values so that they are all relative to the highest value
   * if they are higher than the specified limit.
   * 
   * @param speeds the array of values to process.
   * @param limit  the limit where a value becomes outside of the range
   *               (absolute).
   */
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

  /**
   * Represents a 2-dimensional Vector. Can also be used as a 2-dimensional
   * cartesian point.
   */
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
