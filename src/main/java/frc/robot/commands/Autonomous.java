package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
  public Autonomous() {
    addSequential(new DriveRobot(0, 1, 0, 500));
    addSequential(new TurnRobot(90));
  }
}
