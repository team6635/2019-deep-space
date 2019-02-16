package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveClimberBack extends Command {
  // private double setpoint;
  private double setpoint;

  // public MoveClimberBack(double setpoint) {
  public MoveClimberBack(double setpoint) {
    // super(ms / 1000.0);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climberBack);
    this.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climberBack.setSetpoint(setpoint);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.climberBack.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
