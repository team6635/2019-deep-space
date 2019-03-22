package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveRobotManual extends Command {
  public DriveRobotManual() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (RobotMap.useArcade) {
      double forward = Robot.oi.xbox.getY(Hand.kRight);
      double rotation = Robot.oi.xbox.getX(Hand.kRight);
      Robot.driveTrain.arcadeDrive(forward, rotation);
    } else {
      double leftSpeed = Robot.oi.xbox.getY(Hand.kLeft);
      double rightSpeed = Robot.oi.xbox.getY(Hand.kRight);
      Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
