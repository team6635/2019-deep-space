package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public final class RobotMap {
  // Inputs
  public static final XboxController controller = new XboxController(0);

  // Non-wheel actuators and devices
  public static final AnalogGyro gyro = new AnalogGyro(0);

  // Front left wheel
  public static final VictorSPX motorPivotRearLeft = new VictorSPX(1);
  public static final VictorSPX motorDriveRearLeft = new VictorSPX(2);
  public static final Encoder encoderPivotRearLeft = new Encoder(0, 1);
  public static final SwerveWheel wheelRearLeft = new SwerveWheel((double driveSpeed) -> {
    motorDriveRearLeft.set(ControlMode.PercentOutput, driveSpeed);
  }, () -> {
    // return motorPivotRearLeft.getSelectedSensorPosition();
    return encoderPivotRearLeft.getDistance();
  }, (double pivotSpeed) -> {
    motorPivotRearLeft.set(ControlMode.PercentOutput, pivotSpeed);
  }, -10.5, -11);

  public static final SwerveWheel[] wheels = {
    wheelRearLeft,
  };

  public static final SwerveDrive swerveDrive = new SwerveDrive(wheels, gyro);
}
