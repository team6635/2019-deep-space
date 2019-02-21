package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class SpitCargo extends TimedCommand {
  public SpitCargo(double timeout) {
    super(timeout);
    requires(Robot.cargoIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoIntake.setBoth(-1);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.cargoIntake.setBoth(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
