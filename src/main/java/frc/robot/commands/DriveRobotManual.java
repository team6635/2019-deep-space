/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveRobotManual extends Command {
  public DriveRobotManual() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    var xIn = Robot.oi.xbox.getX(Hand.kRight);
    var yIn = -Robot.oi.xbox.getY(Hand.kRight);
    var zIn = Robot.oi.xbox.getX(Hand.kLeft);

    Robot.driveTrain.swerveDrive(xIn, yIn, zIn);
    // Vector2 leftHandInput = new Vector2(Robot.oi.xbox.getX(Hand.kLeft),
    // Robot.oi.xbox.getY(Hand.kLeft));
    // if (Smath.absAdd(leftHandInput.getX(), leftHandInput.getY()) < 0.25) {
    // Robot.driveTrain.swerveDrive(Robot.oi.xbox.getX(Hand.kRight),
    // Robot.oi.xbox.getY(Hand.kRight));
    // } else {
    // Robot.driveTrain.swerveDrive(Robot.oi.xbox.getX(Hand.kRight),
    // Robot.oi.xbox.getY(Hand.kRight),
    // leftHandInput.getAngle());
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.swerveDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
