package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractBackClimber extends Command {
  public RetractBackClimber() {
    requires(Robot.climberBack);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.climberBack.getSetpoint() != 0)
      Robot.climberBack.setSetpoint(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.climberBack.getSetpoint() == 0 && Robot.climberBack.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climberBack.setSetpoint(Robot.climberBack.getPosition());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
