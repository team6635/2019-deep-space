package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveRobot extends TimedCommand {
  private double x, y, z;

  public DriveRobot(double x, double y, double z, double ms) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(ms / 1000);
    requires(Robot.driveTrain);
    this.x = x;
    this.y = y;
    this.z = z;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.swerveDrive(x, y, z);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.swerveDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
