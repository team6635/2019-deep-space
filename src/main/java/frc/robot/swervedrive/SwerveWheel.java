package frc.robot.swervedrive;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import frc.robot.pid.TimedPID;
import frc.robot.swervedrive.SwerveMath.Point2;

/**
 * Represents a single swerve drive wheel module, including the pivot motor,
 * drive motor, and some form of encoder-like input.
 * 
 * <p>
 * Please note that the {@code driveMotorConsumer} should be set to a motor's
 * {@code .set(double speed)} method. The {@code pivotEncoderSupplier} should be
 * set to the encoder's {@code .getDistance()} method. The
 * {@code pivotMotorConsumer} should be set the same as the other motor.
 * 
 * <p>
 * Note that if using CAN Talons, etc, these may all be different methods. Such
 * as {@code (double s) -> talon.set(Percent, s)} for the motors.
 */
public class SwerveWheel extends TimedPID {
  /**
   * This should be a function that, when called, moves the drive motor. For
   * example, with a PWM motor controller, you can simply pass its
   * {@link edu.wpi.first.wpilibj.SpeedController#set(double) .set(double)}
   * method.
   * 
   * <p>
   * If you are using a CTRE CAN controller, you will need to use a wrapper
   * function around its
   * {@link com.ctre.phoenix.motorcontrol.can.BaseMotorController#set(com.ctre.phoenix.motorcontrol.ControlMode, double)
   * .set(ControlMode.PercentOutput, double)} method (making sure to use
   * {@code ConrolMode.PercentOutput}).
   */
  private DoubleConsumer driveMotorConsumer;
  /**
   * A function that returns the normalized angle of the encoder attached to the
   * pivot motor.
   */
  private DoubleSupplier pivotEncoderSupplier;
  /**
   * Similar to {@link SwerveWheel#driveMotorConsumer} except if must be for
   * output to the pivot motor.
   */
  private DoubleConsumer pivotMotorConsumer;

  /**
   * The location of the center of the wheel relative to the center of rotation of
   * the robot.
   */
  private final Point2 location;
  private final Point2 unitTangent;

  /**
   * Constructs a new {@link SwerveWheel}.
   * 
   * @param driveMotorConsumer   A function for moving the drive motor. See
   *                             {@link SwerveWheel#driveMotorConsumer}.
   * @param pivotEncoderSupplier A function that returns the current encoder
   *                             position for the pivot. See
   *                             {@link SwerveWheel#pivotEncoderSupplier}.
   * @param pivotMotorConsumer   A function for moving the drive motor. See
   *                             {@link SwerveWheel#pivotMotorConsumer}.
   * @param locationX            The location on the X axis of the center of the
   *                             wheel relative to the center of rotation of the
   *                             robot.
   * @param locationY            The location on the Y axis of the center of the
   *                             wheel relative to the center of rotation of the
   *                             robot.
   */
  public SwerveWheel(DoubleConsumer driveMotorConsumer, DoubleSupplier pivotEncoderSupplier,
      DoubleConsumer pivotMotorConsumer, double locationX, double locationY) {
    super(0.015, 0.001, 0.002, 1, 360, 20);

    this.driveMotorConsumer = driveMotorConsumer;
    this.pivotEncoderSupplier = pivotEncoderSupplier;
    this.pivotMotorConsumer = pivotMotorConsumer;

    location = new Point2(locationX, locationY);
    Point2 tangent = new Point2(-location.y, location.x);
    unitTangent = Point2.div(tangent, tangent.magnitude());
  }

  @Override
  public double getPIDInput() {
    return getEncoderAngle();
  }

  @Override
  public void usePIDOutput(double output) {
    pivotMotor(output);
  }

  /**
   * Calls {@link SwerveWheel#driveMotorConsumer driveMotorConsumer} with the
   * specified speed.
   * 
   * @param speed The speed to pass to {@code driveMotorConsumer}
   */
  public void driveMotor(double speed) {
    driveMotorConsumer.accept(speed);
  }

  /**
   * Calls {@code pivotMotorConsumer} with the specified speed.
   * 
   * @param speed The speed to pass to {@code pivotMotorConsumer}
   */
  public void pivotMotor(double speed) {
    pivotMotorConsumer.accept(speed);
  }

  public double getEncoderAngle() {
    return SwerveMath.normalizeAngle(pivotEncoderSupplier.getAsDouble());
  }

  /**
   * Brakes the pivot motor using PID, and zeroes the drive motor output.
   */
  public void brake() {
    setSetpoint(getPIDInput());
    driveMotor(0);
  }

  /**
   * Gets the {@link SwerveWheel#unitTangent unit tangent} for this wheel.
   * 
   * @return The unit tangent vector.
   */
  public Point2 getUnitTangent() {
    return unitTangent;
  }
}
