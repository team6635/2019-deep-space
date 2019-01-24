package frc.robot.extensions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.extensions.Smath.Vector2;

/**
 * This class represents a "swerve module." That is, a combination of both a
 * pivot motor and a driving motor, as well as an encoder for the pivot motor.
 */
public class SwerveWheel {
  /** The motor controller for moving the driving motor. */
  final BaseMotorController drive;
  /** The motor controller for moving the pivot motor. */
  final BaseMotorController pivot;
  /** The encoder that provides feedback for the pivot motor. */
  final Encoder pEncoder;
  /** The unit tangent vector, used in calculations. */
  final Vector2 uTan;
  /** The PID loop for the pivot motor. */
  final SimplePID pid;
  /** If false, the wheel will be passive. */
  boolean isEnabled = false;

  /**
   * Constructs a new wheel.
   * 
   * @param driveMotor   the controller used for the driving motor.
   * @param pivotMotor   the controller used for the pivot motor.
   * @param pivotEncoder the encoder that provides feedback for the pivot motor.
   * @param relX         the position on the X-axis of the wheel itself, relative
   *                     to the center of rotation of the robot.
   * @param relY         the position on the Y-axis of the wheel itself, relative
   *                     to the center of rotation of the robot.
   */
  public SwerveWheel(BaseMotorController driveMotor, BaseMotorController pivotMotor, Encoder pivotEncoder, double relX,
      double relY) {
    // Assign controllers and encoder.
    drive = driveMotor;
    pivot = pivotMotor;
    pEncoder = pivotEncoder;

    // Calculate unit tangent vector.
    uTan = new Vector2(-relY, relX);
    uTan.div(uTan.getMagnitude());

    // Setup PID
    pid = new SimplePID(0.015, 0.001, 0.001) {
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

  /**
   * Sends the specified speed to the driving motor controller as a percent
   * output.
   * 
   * @param speed the speed to drive at, between -1 and 1, inclusively.
   */
  public void updateDriveMotorSpeed(double speed) {
    drive.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Sends the specified speed to the pivot motor controller as a percent output.
   * 
   * @param speed the speed to turn the pivot motor, between -1 and 1,
   *              inclusively.
   */
  public void updatePivotMotorSpeed(double speed) {
    // Ensure enabled status.
    if (!isEnabled) {
      // Log a scary message.
      System.err.println("Tried to call updatePivotMotorSpeed on a disabled wheel!");
      disable();
      return;
    } else {
      enable();
    }
    pivot.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Updates the setpoint of the internal PID loop for the angle of the pivot
   * motor.
   * 
   * @param setpoint the new angle to point the pivot motor at.
   */
  public void updatePIDSetpoint(double setpoint) {
    // Ensure enabled status.
    if (!isEnabled) {
      // Log a scary message.
      System.err.println("Tried to call updatePIDSetpoint on a disabled wheel!");
      disable();
      return;
    } else {
      enable();
    }
    // TODO: For debugging only.
    SmartDashboard.putNumber("setpoint @ " + uTan.getAngle(), setpoint);
    pid.setSetpoint(setpoint);
  }

  /**
   * Gets the normalize, current position of the encoder for the pivot motor.
   * 
   * @return the normalized current value of the encoder.
   */
  public double getCurrentEncoderValue() {
    // TODO: For debugging only:
    SmartDashboard.putNumber("inValue @ " + uTan.getAngle(), Smath.normalizeAngle(pEncoder.getDistance() / 410 * 360));

    // TODO: Move "/410 * 360" to a better way of doing this. This is used for the
    // ticks per revolution on the encoder. Neverest encoders are 410 per
    // revolution. All the math requires this method to return a value between 0 and
    // 360.
    return Smath.normalizeAngle(pEncoder.getDistance() / 410 * 360);
  }

  /**
   * Brakes the PID and drive motor.
   */
  public void brake() {
    updatePIDSetpoint(getCurrentEncoderValue());
    updateDriveMotorSpeed(0);
  }

  /**
   * Gets the unit tangent vector.
   * 
   * @return a copy of the unit tangent vector.
   */
  public Vector2 getUTan() {
    return new Vector2(uTan);
  }

  /**
   * Disables the swerve wheel, disallowing PID control and driving.
   */
  public void disable() {
    isEnabled = false;
    pid.disable();
  }

  /**
   * Enables the swerve wheel, allowing PID control and driving.
   */
  public void enable() {
    isEnabled = true;
    pid.enable();
  }
}
