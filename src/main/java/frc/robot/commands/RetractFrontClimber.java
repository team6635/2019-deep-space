package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractFrontClimber extends Command {
  public RetractFrontClimber() {
    requires(Robot.climberFront);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.climberFront.getSetpoint() != 0)
      Robot.climberFront.setSetpoint(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.climberFront.getSetpoint() == 0 && Robot.climberFront.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climberFront.setSetpoint(Robot.climberFront.getPosition());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
