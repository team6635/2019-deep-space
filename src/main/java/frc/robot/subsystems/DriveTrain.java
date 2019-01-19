/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Smath;
// import frc.robot.swervedrive.SwerveWheel;
import frc.robot.Smath.Vector;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // private final VictorSPX motorDFL = new
  // VictorSPX(RobotMap.pMotorDriveFrontLeft);
  private final VictorSPX motorDFR = new VictorSPX(RobotMap.pMotorDriveFrontRight);
  // private final VictorSPX motorDRL = new
  // VictorSPX(RobotMap.pMotorDriveRearLeft);
  // private final VictorSPX motorDRR = new
  // VictorSPX(RobotMap.pMotorDriveRearRight);
  // private final TalonSRX motorPFL = new
  // TalonSRX(RobotMap.pTalonPivotFrontLeft);
  private final TalonSRX motorPFR = new TalonSRX(RobotMap.pTalonPivotFrontRight);
  // private final TalonSRX motorPRL = new TalonSRX(RobotMap.pTalonPivotRearLeft);
  // private final TalonSRX motorPRR = new
  // TalonSRX(RobotMap.pTalonPivotRearRight);

  private final AnalogGyro gyro = new AnalogGyro(0);

  // private final Wheel wheelFL = new Wheel("WheelFrontLeft", motorDFL, motorPFL,
  // -10, 11);
  private final Wheel wheelFR = new Wheel("WheelFrontRight", motorDFR, motorPFR, 10, 11);
  // private final Wheel wheelRL = new Wheel("WheelRearLeft", motorDRL, motorPRL,
  // -10, -11);
  // private final Wheel wheelRR = new Wheel("WheelRearRight", motorDRR, motorPRR,
  // 10, -11);

  private final Wheel[] wheels = { /* wheelFL, */wheelFR, /* wheelRL, wheelRR, */ };

  @Override
  public void initDefaultCommand() {
  }

  public void drive(double x, double y, double z) {
    double gyroHeading = getHeading();
    double[] speeds = new double[wheels.length];
    if ((Math.abs(x) + Math.abs(y) + Math.abs(z)) / 3 <= 0.08) {
      for (int i = 0; i < wheels.length; i++) {
        wheels[i].brake();
      }
    } else {
      // TODO: if not gyroEnabled return
      for (int i = 0; i < wheels.length; i++) {
        Wheel wheel = wheels[i];
        Vector r = wheel.getUnitTangentVector().dot(z);
        Vector result = new Vector(r.getX() + x, r.getY() + y);
        double angle = result.getAngle();
        double speed = result.getMagnitude();

        if (Math.abs(wheel.returnPIDInput() - angle) > 90) {
          angle += 180;
          speed = -speed;
        }
        angle = Smath.normalizeAngle(angle);

        wheel.setSetpoint(angle);
        speeds[i] = speed;
      }
      Smath.capValues(speeds, 1);
    }

    for (int i = 0; i < wheels.length; i++) {
      wheels[i].driveMotor.set(ControlMode.PercentOutput, speeds[i]);
    }
  }

  public double getHeading() {
    return gyro.getAngle();
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

      driveMotor.setSelectedSensorPosition(0);
      pivotMotor.setSelectedSensorPosition(0);
    }

    public void brake() {
      setSetpoint(returnPIDInput());
      driveMotor.set(ControlMode.PercentOutput, 0);
    }

    public Vector getUnitTangentVector() {
      Vector t = new Vector(-relY, relX);
      return new Vector(t.getX() / t.getMagnitude(), t.getY() / t.getMagnitude());
    }

    @Override
    protected void initDefaultCommand() {
    }

    @Override
    protected void usePIDOutput(double output) {
      pivotMotor.set(ControlMode.PercentOutput, output);
    }

    @Override
    protected double returnPIDInput() {
      return pivotMotor.getSelectedSensorPosition();
    }
  }
}
