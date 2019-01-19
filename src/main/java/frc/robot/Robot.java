package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static DriveTrain drivetrain = new DriveTrain();
  public static OI oi;

  Command autonomousCommand;
  SendableChooser<Command> autonChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    drivetrain = new DriveTrain();
    oi = new OI();
    autonChooser.setDefaultOption("Default Auton", new Autonomous());

    SmartDashboard.putData("Auton Chooser", autonChooser);

    SmartDashboard.putData(drivetrain);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

  @Override
  public void teleopInit() {
    autonomousCommand.cancel();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

  @Override
  public void testPeriodic() {
  }

  private void log() {
    // drivetrain.log();
  }
}
