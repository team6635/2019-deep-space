package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetSucker extends Command {
  private double speed;

  public SetSucker(double speed) {
    requires(Robot.cargoHandler);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.cargoHandler.setSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.cargoHandler.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
