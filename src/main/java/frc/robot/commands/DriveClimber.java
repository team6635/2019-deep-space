package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveClimber extends Command {
  private double speed;

  public DriveClimber(double speed) {
    requires(Robot.climberBack);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climberBack.zoom(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  public synchronized void cancel() {
    super.cancel();
    end();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climberBack.zoom(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
