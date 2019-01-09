package frc.robot.swervedrive;

public class SwerveMath {
  public static double getMaxDoubleBeneathCap(Double[] arr, Double cap) {
    double max = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max && arr[i] < cap) {
        max = arr[i];
      }
    }
    return max;
  }

  public static void capValuesSymmetrically(double[] arr, double cap) {
    if (arr.length == 0)
      return;

    cap = Math.abs(cap);

    double absoluteMax = getAbsoluteMax(arr);

    if (absoluteMax > cap) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == 0)
          continue; // Don't want any division-by-zero happening
        double coef = arr[i] / Math.abs(arr[i]); // -1 if negative, 1 if positive
        arr[i] = arr[i] / absoluteMax * cap * coef;
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

  public static final class Point2 {
    public double x, y;
    
    public Point2() {
      this(0, 0);
    }
    
    public Point2(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public static Point2 add(Point2 a, Point2... os) {
      Point2 r = Point2.copy(a);
      for (Point2 p : os) {
        r.x += p.x;
        r.y += p.y;
      }
      return r;
    }

    public static Point2 add(Point2 a, double od) {
      Point2 o = new Point2(od, od);
      return add(a, o);
    }

    public static Point2 div(Point2 a, Point2... os) {
      Point2 r = Point2.copy(a);
      for (Point2 p : os) {
        r.x /= p.x;
        r.y /= p.y;
      }
      return r;
    }

    public static Point2 div(Point2 a, double od) {
      Point2 o = new Point2(od, od);
      return div(a, o);
    }

    public static Point2 multiply(Point2 a, Point2... os) {
      Point2 r = Point2.copy(a);
      for (Point2 p : os) {
        r.x *= p.x;
        r.y *= p.y;
      }
      return r;
    }

    public static Point2 multiply(Point2 a, double od) {
      Point2 o = new Point2(od, od);
      return multiply(a, o);
    }

    public static Point2 copy(Point2 a) {
      return new Point2(a.x, a.y);
    }

    public double magnitude() {
      return Math.hypot(x, y);
    }
  }

  public static final class SwerveResult {
    public final double angle;
    public final double speed;

    public SwerveResult(double angle, double speed) {
      this.angle = angle;
      this.speed = speed;
    }
  }
}
