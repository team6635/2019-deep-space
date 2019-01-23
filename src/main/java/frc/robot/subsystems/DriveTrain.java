package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;
import frc.robot.extensions.Smath;
import frc.robot.extensions.SwerveDrive;
import frc.robot.extensions.WPIUtils;
import frc.robot.extensions.SwerveWheel;

public class DriveTrain extends Subsystem {
  AnalogGyro gyro = new AnalogGyro(0);

  SwerveWheel wheelFL = new SwerveWheel(new VictorSPX(RobotMap.victorSwerveDriveFL),
      new TalonSRX(RobotMap.talonSwervePivotFL), WPIUtils.encoderDCH(RobotMap.encoderSwerveFL), -10, 11);
  SwerveWheel wheelFR = new SwerveWheel(new VictorSPX(RobotMap.victorSwerveDriveFR),
      new TalonSRX(RobotMap.talonSwervePivotFR), WPIUtils.encoderDCH(RobotMap.encoderSwerveFR), 10, 11);
  SwerveWheel wheelBL = new SwerveWheel(new VictorSPX(RobotMap.victorSwerveDriveBL),
      new TalonSRX(RobotMap.talonSwervePivotBL), WPIUtils.encoderDCH(RobotMap.encoderSwerveBL), -10, -11);
  SwerveWheel wheelBR = new SwerveWheel(new VictorSPX(RobotMap.victorSwerveDriveBR),
      new TalonSRX(RobotMap.talonSwervePivotBR), WPIUtils.encoderDCH(RobotMap.encoderSwerveBR), 10, -11);

  SwerveDrive drive = new SwerveDrive(wheelFL, wheelFR, wheelBL, wheelBR);

  // TODO: Store a z value used for rotation, update it from a PID loop.

  public DriveTrain() {
  }

  public double getHeading() {
    return Smath.normalizeAngle(gyro.getAngle());
  }

  // TODO
  // public void goToHeading(double target) {
  // setSetpoint(Smath.normalizeAngle(target));
  // }

  // public void swerveDrive(double x, double y) {
  // drive.swerveDrive(x, y, z);
  // }

  public void swerveDrive(double x, double y, double z) {
    // goToHeading(targetAngle);
    drive.swerveDrive(x, y, z);
    // drive.swerveDrive(x, y, z);
  }

  public void disable() {
    drive.disable();
  }

  public void enable() {
    drive.enable();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveRobotManual());
  }

  // @Override
  // protected double returnPIDInput() {
  // return getHeading();
  // }

  // @Override
  // protected void usePIDOutput(double output) {
  // z = output;
  // }
}
