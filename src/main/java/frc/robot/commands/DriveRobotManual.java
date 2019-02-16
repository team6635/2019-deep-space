package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.extensions.Smath;

/**
 * This command is the default command for the drivetrain when no other command
 * is scheduled to run. It reads the joystick input, and drives the robot using
 * that.
 */
public class DriveRobotManual extends Command {
  /**
   * Constructs a new instance of this command.
   */
  public DriveRobotManual() {
    // The only dependency we require here is the drivetrain, which we can access
    // from Robot.
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // The only initialization we need to perform is to enable the drivetrain.
    Robot.driveTrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Get the values from the joystick.
    var xIn = Robot.oi.xbox.getX(Hand.kRight);
    var yIn = Robot.oi.xbox.getY(Hand.kRight); // Notice this is inverted, because the joystick is opposite what is
                                               // intuitive (forward is -1).
    var zIn = Robot.oi.xbox.getX(Hand.kLeft); // We are using the X axis of the left hand joystick to provide rotation
                                              // input, since there is no rotation input on the controller. If using a
                                              // controller which provides a Z input, that should be used here.

    // If the absolute sum of the inputs is below a specific boundary, then brake
    // the swerve drive.
    if (Smath.absAdd(xIn, yIn, zIn) < 0.08) {
      Robot.driveTrain.brake();
      return;
    }

    // Drive the robot using the collected inputs.
    // Robot.driveTrain.swerveDrive(xIn / 2, yIn / 2, zIn / 2);
    Robot.driveTrain.swerveDrive(xIn * 2 / 3, yIn * 2 / 3, zIn * 2 / 3);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // This should always return false, since it should never stop running during
    // the match. When another command is running on the drivetrain, this one sits
    // in the background, waiting for there to be no command running that is using
    // the drivetrain.
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Simply stop the robot.
    Robot.driveTrain.swerveDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
