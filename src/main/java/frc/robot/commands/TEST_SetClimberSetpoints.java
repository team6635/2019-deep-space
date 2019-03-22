package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * For testing only. Sets climber's setpoint so we can test it at different
 * places.
 */
public class TEST_SetClimberSetpoints extends Command {
  public final double setpoint_front;
  public final double setpoint_back;

  public TEST_SetClimberSetpoints(double setpoint_front, double setpoint_back) {
    requires(Robot.climberBack);
    requires(Robot.climberFront);

    this.setpoint_front = setpoint_front;
    this.setpoint_back = setpoint_back;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.climberFront.getSetpoint() != setpoint_front)
      Robot.climberFront.setSetpoint(setpoint_front);
    if (Robot.climberBack.getSetpoint() != setpoint_back)
      Robot.climberBack.setSetpoint(setpoint_back);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.climberFront.getSetpoint() == setpoint_front && Robot.climberBack.getSetpoint() == setpoint_back
        && Robot.climberFront.onTarget() && Robot.climberBack.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
