package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SwitchDriveMethod extends InstantCommand {
  public SwitchDriveMethod() {
    super();
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.useArcadeDrive = !Robot.driveTrain.useArcadeDrive;
  }
}
