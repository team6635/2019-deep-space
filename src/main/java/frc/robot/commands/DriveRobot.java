package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * This is a command for controlling the swerve drive with the specified inputs
 * for a specific amount of time.
 */
public class DriveRobot extends TimedCommand {
  /**
   * Stored value from the command's construction.
   */
  private double x, y, z;

  /**
   * Creates a new instance of the command.
   * 
   * @param x  the input for the X value, as if it were coming from a controller.
   * @param y  the input for the Y value, as if it were coming from a controller.
   * @param z  the input for the Z (rotation) value, as if it were coming from a
   *           controller.
   * @param ms the amount of time, in milliseconds, to drive the robot with these
   *           inputs.
   */
  public DriveRobot(double x, double y, double z, double ms) {
    // Call super to pass the time to the TimedCommand constructor. Since that
    // constructor accepts time in the form of seconds, we need to convert our
    // milliseconds to seconds by dividing by 1000.
    super(ms / 1000.0);

    // Subsystem dependencies go here, using the requires method. Since this command
    // only needs the drivetrain in order to drive, that is all we are requiring.
    requires(Robot.driveTrain);

    // Store the x, y, and z values that were passed to this constructor in the
    // instance properties, so they can be used in other methods of the command.
    this.x = x;
    this.y = y;
    this.z = z;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Our only initialization is to enable the drivetrain.
    Robot.driveTrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Drive the robot using the stored x, y, and z values.
    Robot.driveTrain.swerveDrive(x, y, z);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // isTimedOut will return true if the time passed in the constructor has
    // elapsed.
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Stop driving the robot. Note: do not disable the drivetrain here.
    Robot.driveTrain.swerveDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
