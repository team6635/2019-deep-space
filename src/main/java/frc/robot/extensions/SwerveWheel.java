package frc.robot.extensions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.extensions.Smath.Vector2;

public class SwerveWheel {
  final BaseMotorController drive;
  final BaseMotorController pivot;
  final Encoder pEncoder;
  final Vector2 uTan;
  final SimplePID pid;

  public SwerveWheel(BaseMotorController driveMotor, BaseMotorController pivotMotor, Encoder pivotEncoder, double relX,
      double relY) {
    drive = driveMotor;
    pivot = pivotMotor;
    pEncoder = pivotEncoder;

    uTan = new Vector2(-relY, relX);
    uTan.div(uTan.getMagnitude());

    // Setup PID
    pid = new SimplePID(0.015, 0, 0.001) {
      @Override
      public void usePIDOutput(double output) {
        updatePivotMotorSpeed(output);
      }

      @Override
      public double returnPIDInput() {
        return getCurrentEncoderValue();
      }
    };
    pid.setTolerance(0);
  }

  public void updateDriveMotorSpeed(double speed) {
    drive.set(ControlMode.PercentOutput, speed);
  }

  public void updatePivotMotorSpeed(double speed) {
    pivot.set(ControlMode.PercentOutput, speed);
  }

  public void updatePIDSetpoint(double setpoint) {
    SmartDashboard.putNumber("setpoint @ " + uTan.getAngle(), setpoint);
    pid.setSetpoint(setpoint);
  }

  public double getCurrentEncoderValue() {
    SmartDashboard.putNumber("inValue @ " + uTan.getAngle(), Smath.normalizeAngle(pEncoder.getDistance() / 410 * 360));

    return Smath.normalizeAngle(pEncoder.getDistance() / 410 * 360);
  }

  public Vector2 getUTan() {
    return new Vector2(uTan);
  }

  public void disable() {
    pid.disable();
  }

  public void enable() {
    pid.enable();
  }
}
