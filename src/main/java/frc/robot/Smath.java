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

  public static void capValues(double[] arr, double lim) {
    if (arr.length == 0)
      return;
    lim = Math.abs(lim);
    double aMax = getAbsoluteMax(arr);
    if (aMax > lim) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == 0)
          continue;
        double coef = arr[i] / Math.abs(arr[i]);
        arr[i] = arr[i] / aMax * lim * coef;
      }
    }
  }

  public static double getAbsoluteMax(double[] arr) {
    return Math.max(Math.abs(getMaxDouble(arr)), Math.abs(getMinDouble(arr)));
  }

  public static double getMaxDouble(double[] arr) {
    if (arr.length == 0)
      return Double.NaN;
    double max = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max)
        max = arr[i];
    }
    return max;
  }

  public static final double getMinDouble(double[] arr) {
    if (arr.length == 0)
      return Double.NaN;
    double min = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < min)
        min = arr[i];
    }
    return min;
  }

  public static final class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public void setX(double x) {
      this.x = x;
    }

    public void setY(double y) {
      this.y = y;
    }
  }

  public static final class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
      set(x, y);
    }

    public double getAngle() {
      return Smath.normalizeAngle(Math.atan2(y, x) * (180 / Math.PI) - 90);
    }

    public Vector dot(double m) {
      return new Vector(x * m, y * m);
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
      double rawSpeed = getMagnitude();
      setX(Math.cos(newAngle + 90) * rawSpeed);
      setY(Math.sin(newAngle + 90) * rawSpeed);
    }
  }
}
