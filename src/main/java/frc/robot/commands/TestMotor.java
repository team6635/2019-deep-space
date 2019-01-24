package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This command uses the {@link frc.robot.subsystems.Tester Tester} subsystem in
 * order to move a single motor.
 */
public class TestMotor extends Command {
  /** The speed input for the motor. */
  double speed;

  /**
   * Create a new instance of the command.
   */
  public TestMotor(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.tester);

    // Store the speed parameter as a property.
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Turn the motor with the stored speed.
    Robot.tester.moveMotor(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.tester.stop();
  }

  @Override
  public synchronized void cancel() {
    super.cancel();
    end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
