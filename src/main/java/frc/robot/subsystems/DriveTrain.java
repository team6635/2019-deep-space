package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;

public class DriveTrain extends Subsystem {
  private SpeedController backLeft = new WPI_VictorSPX(RobotMap.victorDriveBL);
  private SpeedController backRight = new WPI_VictorSPX(RobotMap.victorDriveBR);
  private SpeedController frontLeft = new WPI_VictorSPX(RobotMap.victorDriveFL);
  private SpeedController frontRight = new WPI_VictorSPX(RobotMap.victorDriveFR);

  private SpeedControllerGroup left = new SpeedControllerGroup(backLeft, frontLeft);
  private SpeedControllerGroup right = new SpeedControllerGroup(backRight, frontRight);

  private DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public void arcadeDrive(double forward, double rotation) {
    diffDrive.arcadeDrive(forward, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    diffDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void stop() {
    diffDrive.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveRobotManual());
  }
}
