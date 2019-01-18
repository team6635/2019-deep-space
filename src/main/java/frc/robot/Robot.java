package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  private Command autonomousCommand;

  public static DriveTrain drivetrain;
  public static OI oi;

  @Override
  public void robotInit() {
    drivetrain = new DriveTrain();
    oi = new OI();

    SmartDashboard.putData(drivetrain);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    drivetrain.disable();
    MecanumDrive m;
    m.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  @Override

  public void disabledPeriodic() {
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
    drivetrain.log();
  }
}
