package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class SpitCargo extends TimedCommand {
  public SpitCargo(double ms) {
    super(ms / 1000.0);
    requires(Robot.cargoHandler);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoHandler.setSpeed(1.0);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.cargoHandler.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
