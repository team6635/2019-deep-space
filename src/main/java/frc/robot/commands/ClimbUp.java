package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class ClimbUp extends CommandGroup {
  public ClimbUp() {
    addParallel(new MoveClimberBack(RobotMap.kClimberBackExtended)); // climb both climbers
    addSequential(new MoveClimberFront(RobotMap.kClimberFrontExtended));
  }
}
