package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveTrain;

/**
 * This is the main class of the robot. The RoboRIO is set up to automatically
 * run this code first when starting the robot.
 */
public class Robot extends TimedRobot {
  public static OI oi;

  // Initialize all subsystems.
  public static DriveTrain driveTrain = new DriveTrain();

  /**
   * This method is called once, when the robot boots up. Any initialization
   * should be done in here, aside from the subsystems.
   */
  @Override
  public void robotInit() {
    // Do not move this. Since the OI may use some commands, those commands might
    // get null pointers for any required subsystems if the OI is called during the
    // Robot class' construction.
    oi = new OI();
  }

  /**
   * This method is called once, when autonomous mode starts.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This method is called constantly during autonomous mode.
   */
  @Override
  public void autonomousPeriodic() {
    // This runs all current commands.
    Scheduler.getInstance().run();
  }

  /**
   * This method is called once, when teleoperated mode begins.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This method is called constantly during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    // This runs all current commands.
    Scheduler.getInstance().run();
  }

  /**
   * This method is called constantly while the robot is on and not Emergency
   * Stopped; regardless of mode.
   */
  @Override
  public void robotPeriodic() {
    // Call the log method so we can get some fun telemetry going.
    log();
  }

  /**
   * Log interesting stats to the SmartDashboard
   */
  private void log() {
    driveTrain.log();
  }
}
