package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.swervedrive.SwerveDrive;

public class Robot extends TimedRobot {
  private final SwerveDrive drive = RobotMap.swerveDrive;
  private final XboxController controller = RobotMap.controller;

  @Override
  public void robotInit() {
    drive.disable();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    drive.disable();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    drive.enable();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    drive.enable();
  }

  @Override
  public void teleopPeriodic() {
    drive.drive(controller.getX(Hand.kRight), controller.getY(Hand.kRight), controller.getX(Hand.kLeft));
  }

  @Override
  public void testPeriodic() {
  }
}
