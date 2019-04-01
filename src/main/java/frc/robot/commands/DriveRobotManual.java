package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveRobotManual extends Command {
  public DriveRobotManual() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    final double coef = 0.75;

    if (Robot.driveTrain.useArcadeDrive) {
      Robot.driveTrain.arcadeDrive(-Robot.oi.xbox.getY(Hand.kRight), Robot.oi.xbox.getX(Hand.kRight));
    } else {
      Robot.driveTrain.tankDrive(-Robot.oi.xbox.getY(Hand.kLeft) * coef, -Robot.oi.xbox.getY(Hand.kRight) * coef);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
