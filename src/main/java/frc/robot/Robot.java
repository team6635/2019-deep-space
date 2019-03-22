package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.ClimberBack;
import frc.robot.subsystems.ClimberBackDriver;
import frc.robot.subsystems.ClimberFront;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.CargoIntake;

/**
 * This is the main class of the robot. The RoboRIO is set up to automatically
 * run this code first when starting the robot.
 */
public class Robot extends TimedRobot {
  public static OI oi;

  // Initialize subsystems.
  public static CargoIntake cargoIntake = new CargoIntake();
  public static ClimberBack climberBack = new ClimberBack();
  public static ClimberBackDriver climberBackDriver = new ClimberBackDriver();
  public static ClimberFront climberFront = new ClimberFront();
  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();
  public static HatchIntake hatchIntake = new HatchIntake();

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

    // Start camera stream
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This method is called once, when autonomous mode starts.
   */
  @Override
  public void autonomousInit() {
    enableSubsystems();
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
    enableSubsystems();
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
   * This method is called once, when disabled mode begins.
   */
  @Override
  public void disabledInit() {
    disableSubsystems();
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

  /**
   * Disable subsystems that need enabling (usually those with PID controllers).
   */
  private void disableSubsystems() {
    climberBack.disable();
    climberFront.disable();
    driveTrain.disable();
    elevator.disable();
    hatchIntake.disable();
  }

  /**
   * Enable subsystems that need enabling (usually those with PID controllers).
   */
  private void enableSubsystems() {
    climberBack.enable();
    climberFront.enable();
    driveTrain.enable();
    // elevator.enable(); // TODO: We are not using our elevator rn.
    hatchIntake.enable();
  }
}
