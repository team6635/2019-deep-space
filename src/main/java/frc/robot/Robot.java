package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.TestAuton;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Tester;

/**
 * This is the main class of the robot. The RoboRIO is set up to automatically
 * run this code first when starting the robot.
 */
public class Robot extends TimedRobot {
  /** The command selected in the {@link SmartDashboard} for autonomous mode. */
  Command autonomousCommand;
  /** The human interface devices. An instance of {@link OI}. */
  public static OI oi;

  // Initialize all subsystems.
  /** The {@link DriveTrain} instance of the robot. A subsystem. */
  public static DriveTrain driveTrain = new DriveTrain();
  /** The {@link Tester} instance of the robot. A subsystem. */
  public static Tester tester = new Tester();

  // Initialize the autonomous chooser.
  /**
   * This is an instance of {@link SendableChooser}. This provides a dropdown menu
   * on the Driver Station, allowing the driver to select which autonomous command
   * to run.
   */
  public SendableChooser<Command> autonChooser;

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

    // Initialize and send the autonomous chooser to the Driver Station (via
    // SmartDashboard).
    autonChooser = new SendableChooser<>();
    autonChooser.setDefaultOption("TestAuton", new TestAuton());
    SmartDashboard.putData("Auton Mode", autonChooser);
  }

  /**
   * This method is called once, when autonomous mode starts.
   */
  @Override
  public void autonomousInit() {
    // Get the autonomous command that the driver selected from the dropdown menu.
    // If a command was selected (not null), then we will start that command.
    autonomousCommand = autonChooser.getSelected();
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
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
    // If an autonomous command was selected, we call its cancel method to ensure
    // that it is terminated before we begin teleoperated control.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
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
    // Coming Soon: Some fun telemetry!
    // TODO
  }
}
