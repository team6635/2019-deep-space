package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbUp extends CommandGroup {
  public ClimbUp() {
    addParallel(new MoveClimberBack(4085.75)); // climb both climbers
    addSequential(new MoveClimberFront(-3623.0));

    addSequential(new DriveClimberTimed(3.0, 1.0)); // drive on stilts for 3 seconds

    addSequential(new MoveClimberFront(0)); // lower front climber

    addSequential(new DriveClimberTimed(6.0, 1.0)); // drive on back stilts for 6 seconds

    addSequential(new MoveClimberBack(0)); // lower back climber
  }
}
