/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.swervedrive.SwerveWheel;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private final VictorSPX motorDFL = new VictorSPX(RobotMap.pMotorDriveFrontLeft);
  private final VictorSPX motorDFR = new VictorSPX(RobotMap.pMotorDriveFrontRight);
  private final VictorSPX motorDRL = new VictorSPX(RobotMap.pMotorDriveRearLeft);
  private final VictorSPX motorDRR = new VictorSPX(RobotMap.pMotorDriveRearRight);
  private final TalonSRX motorPFL = new TalonSRX(RobotMap.pMotorPivotFrontLeft);
  private final TalonSRX motorPFR = new TalonSRX(RobotMap.pMotorPivotFrontRight);
  private final TalonSRX motorPRL = new TalonSRX(RobotMap.pMotorPivotRearLeft);
  private final TalonSRX motorPRR = new TalonSRX(RobotMap.pMotorPivotRearRight);

  private final Wheel wheelFL = new Wheel("WheelFrontLeft", motorDFL, motorPFL, -10, 11);
  private final Wheel wheelFR = new Wheel("WheelFrontRight", motorDFR, motorPFR, 10, 11);
  private final Wheel wheelRL = new Wheel("WheelRearLeft", motorDRL, motorPRL, -10, -11);
  private final Wheel wheelRR = new Wheel("WheelRearRight", motorDFR, motorPRR, 10, -11);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  private final class Wheel extends PIDSubsystem {
    private final double relX;
    private final double relY;
    private final BaseMotorController driveMotor;
    private final TalonSRX pivotMotor;

    public Wheel(String name, BaseMotorController drive, TalonSRX pivot, double relX, double relY) {
      super(name, 0.015, 0.001, 0.002);
      this.relX = relX;
      this.relY = relY;
      this.driveMotor = drive;
      this.pivotMotor = pivot;
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    protected void usePIDOutput(double output) {

    }

    @Override
    protected double returnPIDInput() {
      return 0;
    }
  }
}
