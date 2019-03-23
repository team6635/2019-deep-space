package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbDown extends CommandGroup {
  public ClimbDown() {
    addParallel(new RetractBackClimber());
    addSequential(new RetractFrontClimber());
  }
}
