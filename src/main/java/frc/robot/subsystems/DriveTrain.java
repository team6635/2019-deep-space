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
  AHRS gyro = new AHRS(SPI.Port.kMXP);

  // Neverest 60 motors emit 420 pulses per revolution.
  SwerveWheel wheelFL = new SwerveWheel(new WPI_VictorSPX(RobotMap.victorSwerveDriveFL),
      new WPI_TalonSRX(RobotMap.talonSwervePivotFL), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveFL, 420.0), -10.25,
      10.25);
  SwerveWheel wheelFR = new SwerveWheel(new WPI_VictorSPX(RobotMap.victorSwerveDriveFR),
      new WPI_TalonSRX(RobotMap.talonSwervePivotFR), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveFR, 420.0), 10.25,
      10.25);
  SwerveWheel wheelBL = new SwerveWheel(new WPI_VictorSPX(RobotMap.victorSwerveDriveBL),
      new WPI_TalonSRX(RobotMap.talonSwervePivotBL), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveBL, 420.0), -10.25,
      -10.25);
  SwerveWheel wheelBR = new SwerveWheel(new WPI_VictorSPX(RobotMap.victorSwerveDriveBR),
      new WPI_TalonSRX(RobotMap.talonSwervePivotBR), WPIUtils.angleEncoderDCH(RobotMap.encoderSwerveBR, 420.0), 10.25,
      -10.25);

  SwerveDrive drive = new SwerveDrive(wheelFL, wheelFR, wheelBL, wheelBR);

  public DriveTrain() {
  }

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
