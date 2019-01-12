package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  Command auton;

  public static DriveTrain drivetrain;
  public static OI oi;

  @Override
  public void robotInit() {
    drivetrain = new DriveTrain();
    oi = new OI();

    auton = new Autonomous();

    SmartDashboard.putData(drivetrain);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    drivetrain.disable();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    auton.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    log();
  }

  @Override
  public void teleopInit() {
    auton.cancel();
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
    drivetrain.log();
  }
}
