package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbDown extends CommandGroup {
  public ClimbDown() {
    addParallel(new MoveClimberBack(0));
    addSequential(new MoveClimberFront(0));
  }
}
