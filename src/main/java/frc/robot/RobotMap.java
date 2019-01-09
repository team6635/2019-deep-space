package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public final class RobotMap {
  // Inputs
  public static final XboxController controller = new XboxController(0);

  // Non-wheel actuators and devices
  public static final AnalogGyro gyro = new AnalogGyro(0);

  // Front left wheel
  public static final VictorSPX motorPivotFrontLeft = new VictorSPX(1);
  public static final VictorSPX motorDriveFrontLeft = new VictorSPX(2);
  public static final SwerveWheel wheelFrontLeft = new SwerveWheel((double driveSpeed) -> {
    motorDriveFrontLeft.set(ControlMode.PercentOutput, driveSpeed);
  }, () -> {
    return motorPivotFrontLeft.getSelectedSensorPosition();
  }, (double pivotSpeed) -> {
    motorPivotFrontLeft.set(ControlMode.PercentOutput, pivotSpeed);
  }, -13, 11);

  public static final SwerveWheel[] wheels = {
    wheelFrontLeft,
  };

  public static final SwerveDrive swerveDrive = new SwerveDrive(wheels, gyro);
}
