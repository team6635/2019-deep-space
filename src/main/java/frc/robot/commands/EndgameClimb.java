package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EndgameClimb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public EndgameClimb() {
    addParallel(new MoveClimberFront(-3623.0));
    addSequential(new MoveClimberBack(4085.76));
    addSequential(new DriveClimberTimed(5, 1.0));

    // addSequential(new MoveClimberFront(0.0));
    // addSequential(new DriveClimberTimed(7.0, 1.0));
    // addSequential(new MoveClimberBack(0.0));

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
