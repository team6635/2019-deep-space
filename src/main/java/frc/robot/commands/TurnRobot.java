package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class TurnRobot extends PIDCommand {
  public TurnRobot(double angle) {
    super(0.0015, 0.0001, 0.0025);
    requires(Robot.drivetrain);
    getPIDController().setPercentTolerance(0.5);
    setSetpoint(angle);
  }

  @Override
  protected double returnPIDInput() {
    return Robot.drivetrain.getHeading();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.drivetrain.drive(0, 0, output);
  }

  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }
}
