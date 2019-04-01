package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;

public class DriveTrain extends Subsystem {
  private WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.frontLeft),
      frontRight = new WPI_VictorSPX(RobotMap.frontRight), backLeft = new WPI_VictorSPX(RobotMap.backLeft),
      backRight = new WPI_VictorSPX(RobotMap.backRight);

  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;

  private DifferentialDrive drive;

  public boolean useArcadeDrive = false;

  public DriveTrain() {
    leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    rightMotors = new SpeedControllerGroup(frontRight, backRight);
    drive = new DifferentialDrive(leftMotors, rightMotors);
    // drive.setDeadband(0.09);
  }

  public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
    drive.arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void stop() {
    drive.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveRobotManual());
  }
}
