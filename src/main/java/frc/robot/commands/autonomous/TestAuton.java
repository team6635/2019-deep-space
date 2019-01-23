package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveRobot;

/**
 * This is a command group used for testing autonomous code.
 */
public class TestAuton extends CommandGroup {
  public TestAuton() {
    addSequential(new DriveRobot(0, 0.5, 0, 1500));
    addSequential(new DriveRobot(0, -0.5, 0, 1500));
    addSequential(new DriveRobot(0, 0, 0.5, 1500));
  }
}
