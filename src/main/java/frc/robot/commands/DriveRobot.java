package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveRobot extends TimedCommand {
  private double x, y, z;

  public DriveRobot(double x, double y, double z, double ms) {
    super(ms / 1000);
    requires(Robot.drivetrain);
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  protected void initialize() {
    Robot.drivetrain.drive(x, y, z);
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}
