package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;
import frc.robot.extensions.SwerveDrive;
import frc.robot.extensions.WPIUtils;
import frc.robot.extensions.SwerveWheel;

public class DriveTrain extends Subsystem {
  // AnalogGyro gyro = new AnalogGyro(0);
  AHRS gyro = new AHRS(SPI.Port.kMXP);
  // Neverest 60 motors emit 420 pulses per revolution.
  SwerveWheel wheelFL = new SwerveWheel(new WPI_VictorSPX(RobotMap.pSwerveDriveFL),
      new WPI_TalonSRX(RobotMap.pSwervePivotFL), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveFL, 420.0), -11, 11.5);
  SwerveWheel wheelFR = new SwerveWheel(new WPI_VictorSPX(RobotMap.pSwerveDriveFR),
      new WPI_TalonSRX(RobotMap.pSwervePivotFR), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveFR, 420.0), 11, 11.5);
  SwerveWheel wheelBL = new SwerveWheel(new WPI_VictorSPX(RobotMap.pSwerveDriveBL),
      new WPI_TalonSRX(RobotMap.pSwervePivotBL), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveBL, 420.0), -11, -11.5);
  SwerveWheel wheelBR = new SwerveWheel(new WPI_VictorSPX(RobotMap.pSwerveDriveBR),
      new WPI_TalonSRX(RobotMap.pSwervePivotBR), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveBR, 420.0), 11, -11.5);

  SwerveDrive drive = new SwerveDrive(wheelFL, wheelFR, wheelBL, wheelBR);

  public DriveTrain() {
  }

  // public double getHeading() {
  // return Smath.normalizeAngle(gyro.getAngle());
  // }

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

  /**
   * Brakes all wheels.
   */
  public void brake() {
    for (var wheel : drive.getWheels()) {
      wheel.brake();
    }
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

  public void log() {
    // SmartDashboard.putNumber("Heading", getHeading());
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
