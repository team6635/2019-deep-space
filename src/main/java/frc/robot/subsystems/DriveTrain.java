package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;

public class DriveTrain extends Subsystem {
  private WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.victorSPX_FrontLeft),
      frontRight = new WPI_VictorSPX(RobotMap.victorSPX_FrontRight),
      backLeft = new WPI_VictorSPX(RobotMap.victorSPX_BackLeft),
      backRight = new WPI_VictorSPX(RobotMap.victorSPX_BackRight);
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  private DifferentialDrive drive;

  public DriveTrain() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    rightMotors = new SpeedControllerGroup(frontRight, backRight);
    drive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
    drive.arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    // var frontLeftCurrent = Robot.pdp.getCurrent(0);
    // var frontRightCurrent = Robot.pdp.getCurrent(15);
    // var backLeftCurrent = Robot.pdp.getCurrent(1);
    // var backRightCurrent = Robot.pdp.getCurrent(14);

    // var leftCurrent = frontLeftCurrent + backLeftCurrent;
    // var rightCurrent = frontRightCurrent + backRightCurrent;

    // if (leftCurrent > 90) {
    // leftSpeed *= 1 - ((leftCurrent - 90) / (160 - 90));
    // }

    // if (rightCurrent > 90) {
    // rightSpeed *= 1 - ((rightCurrent - 90) / (160 - 90));
    // }

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
