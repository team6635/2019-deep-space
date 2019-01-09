package frc.robot.swervedrive;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import frc.robot.pid.TimedPID;
import frc.robot.swervedrive.SwerveMath.Point2;
import frc.robot.swervedrive.SwerveMath.SwerveResult;;

/**
 * Represents a single swerve drive wheel module, including the
 * pivot motor, drive motor, and some form of encoder-like input.
 * 
 * <p> Please note that the {@code driveMotorConsumer} should be set
 * to a motor's {@code .set(double speed)} method. The {@code pivotEncoderSupplier}
 * should be set to the encoder's {@code .getDistance()} method. The 
 * {@code pivotMotorConsumer} should be set the same as the other motor.
 * 
 * <p> Note that if using CAN Talons, etc, these may all be different
 * methods. Such as {@code (double s) -> victor.set(Percent, s)} for 
 * the motors.
 */
public class SwerveWheel extends TimedPID {
  private DoubleConsumer driveMotorConsumer;
  private DoubleSupplier pivotEncoderSupplier;
  private DoubleConsumer pivotMotorConsumer;

  private final Point2 location;
  private final Point2 tangent;
  private final Point2 unitTangent;

  public SwerveWheel(DoubleConsumer driveMotorConsumer, DoubleSupplier pivotEncoderSupplier, DoubleConsumer pivotMotorConsumer, double locationX, double locationY) {
    super(0.015, 0.001, 0.0020, 1, 360, 20);

    this.driveMotorConsumer = driveMotorConsumer;
    this.pivotEncoderSupplier = pivotEncoderSupplier;
    this.pivotMotorConsumer = pivotMotorConsumer;

    this.location = new Point2(locationX, locationY);
    this.tangent = new Point2(-location.y, location.x);
    this.unitTangent = Point2.div(tangent, tangent.magnitude());
  }

  public SwerveResult calculate(double xInput, double yInput, double zInput) {
    Point2 r = Point2.multiply(unitTangent, zInput);
    Point2 result = Point2.add(r, new Point2(xInput, yInput));

    double angle = Math.atan2(result.y, result.x) * (180 / Math.PI) - 90 % 360;
    double speed = result.magnitude();

    return new SwerveResult(angle, speed);
  }

  @Override
  public double getPIDInput() {
    // return ((encoderPivot.getDistance() % 360) + 360) % 360;
    return ((pivotEncoderSupplier.getAsDouble() % 360) + 360) % 360;
  }

  @Override
  public void usePIDOutput(double output) {
    pivotMotorConsumer.accept(output);
  }

  /**
   * @return the drive motor's consumer
   */
  public DoubleConsumer getDriveMotorConsumer() {
    return driveMotorConsumer;
  }

  /**
   * @return the pivot encoder's supplier
   */
  public DoubleSupplier getPivotEncoderSupplier() {
    return pivotEncoderSupplier;
  }

  /**
   * @return the pivot motor's consumer
   */
  public DoubleConsumer getPivotMotorConsumer() {
    return pivotMotorConsumer;
  }
}
