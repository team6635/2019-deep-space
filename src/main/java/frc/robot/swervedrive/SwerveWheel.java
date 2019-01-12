package frc.robot.swervedrive;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import frc.robot.pid.SwervePID;
import frc.robot.swervedrive.SwerveMath.Point2;

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
 * methods. Such as {@code (double s) -> talon.set(Percent, s)} for 
 * the motors.
 */
public class SwerveWheel extends SwervePID {
  private DoubleConsumer driveMotorConsumer;
  private DoubleSupplier pivotEncoderSupplier;
  private DoubleConsumer pivotMotorConsumer;

  private final Point2 location;
  private final Point2 tangent;
  private final Point2 unitTangent;

  public SwerveWheel(DoubleConsumer driveMotorConsumer, DoubleSupplier pivotEncoderSupplier, DoubleConsumer pivotMotorConsumer, double locationX, double locationY) {
    super(0.015, 0.001, 0.0020, 20, 1);

    this.driveMotorConsumer = driveMotorConsumer;
    this.pivotEncoderSupplier = pivotEncoderSupplier;
    this.pivotMotorConsumer = pivotMotorConsumer;

    this.location = new Point2(locationX, locationY);
    this.tangent = new Point2(-location.y, location.x);
    this.unitTangent = Point2.div(tangent, tangent.magnitude());
  }

  @Override
  public double pidInputProvider() {
    return getEncoderAngle();
  }

  @Override
  public void pidUseOutput(double output) {
    pivotMotor(output);
  }

  /**
   * Calls {@code driveMotorConsumer} with the specified speed.
   * @param speed The speed to pass to {@code driveMotorConsumer}
   */
  public void driveMotor(double speed) {
    driveMotorConsumer.accept(speed);
  }

  /**
   * Calls {@code pivotMotorConsumer} with the specified speed.
   * @param speed The speed to pass to {@code pivotMotorConsumer}
   */
  public void pivotMotor(double speed) {
    pivotMotorConsumer.accept(speed);
  }

  public double getEncoderAngle() {
    return SwerveMath.normalizeAngle(pivotEncoderSupplier.getAsDouble());
  }

  /**
   * Brakes the pivot motor using PID, and zeroes the drive motor
   * output.
   */
  public void brake() {
    setSetpoint(pidInputProvider());
    driveMotor(0);
  }

  public Point2 getUnitTangent() {
    return unitTangent;
  }
}
