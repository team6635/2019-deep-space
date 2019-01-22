package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.TestAuton;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  Command autonomousCommand;
  public static OI oi;

  // Init subsystems
  public static DriveTrain driveTrain = new DriveTrain();

  public SendableChooser<Command> autonChooser;

  @Override
  public void robotInit() {
    oi = new OI(); // Don't move this

    // Send the auton chooser
    autonChooser = new SendableChooser<>();
    autonChooser.setDefaultOption("TestAuton", new TestAuton());

    SmartDashboard.putData("Auton Mode", autonChooser);
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = autonChooser.getSelected();
    autonomousCommand.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void robotPeriodic() {
    log();
  }

  /**
   * Log interesting stats to the SmartDashboard
   */
  private void log() {
  }
}
