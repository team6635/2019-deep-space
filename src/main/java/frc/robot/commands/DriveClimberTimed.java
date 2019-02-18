package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveClimberTimed extends TimedCommand {
  private double speed;

  public DriveClimberTimed(double timeout, double speed) {
    super(timeout);
    requires(Robot.climberBackDriver);
    this.speed = speed;
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climberBackDriver.setSpeed(speed);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.climberBackDriver.stop();
  }

  @Override
  public synchronized void cancel() {
    super.cancel();
    end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
