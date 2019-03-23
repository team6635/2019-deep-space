package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class MoveHatcherTimed extends TimedCommand {
  private final double speed;

  public MoveHatcherTimed(double ms, double speed) {
    super(ms / 1000.0);
    requires(Robot.hatchIntake);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatchIntake.setPower(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatchIntake.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
