package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public final class RobotMap {
  // Inputs
  public static final XboxController controller = new XboxController(0);

  // Non-wheel actuators and devices
  public static final AnalogGyro gyro = new AnalogGyro(0);

  // Rear left wheel
  public static final VictorSPX motorPivotRearLeft = new VictorSPX(1);
  public static final VictorSPX motorDriveRearLeft = new VictorSPX(2);
  public static final Encoder encoderPivotRearLeft = new Encoder(0, 1, true, EncodingType.k4X);
  public static final SwerveWheel wheelRearLeft = new SwerveWheel((double driveSpeed) -> {
    motorDriveRearLeft.set(ControlMode.PercentOutput, driveSpeed / 3);
  }, () -> {
    return encoderPivotRearLeft.getDistance();
  }, (double pivotSpeed) -> {
    motorPivotRearLeft.set(ControlMode.PercentOutput, pivotSpeed);
  }, -10.5, -11);

  // Rear left wheel
  public static final VictorSPX motorPivotFrontRight = new VictorSPX(5);
  public static final VictorSPX motorDriveFrontRight = new VictorSPX(6);
  public static final Encoder encoderPivotFrontRight = new Encoder(2, 3, true, EncodingType.k4X);
  public static final SwerveWheel wheelFrontRight = new SwerveWheel((double driveSpeed) -> {
    motorDriveFrontRight.set(ControlMode.PercentOutput, driveSpeed / 3);
  }, () -> {
    return encoderPivotFrontRight.getDistance();
  }, (double pivotSpeed) -> {
    motorPivotFrontRight.set(ControlMode.PercentOutput, pivotSpeed);
  }, 10.5, 11);

  public static final SwerveWheel[] wheels = {
    wheelRearLeft,
    wheelFrontRight,
  };

  public static final SwerveDrive swerveDrive = new SwerveDrive(wheels, gyro);
}
