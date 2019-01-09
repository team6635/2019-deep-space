package frc.robot.swervedrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.swervedrive.SwerveMath.SwerveResult;

/**
 * A container around swerve wheels.
 */
public class SwerveDrive {
  private SwerveWheel[] wheels;
  private double controllerTolerance = 0.08;

  public SwerveDrive(SwerveWheel[] wheels, AnalogGyro gyro) {
    this.wheels = wheels;
    // TODO: Gyro
  }

  public void drive(double xInput, double yInput, double zInput) {
    double[] speeds = new double[wheels.length];

    if (Math.abs(xInput) + Math.abs(yInput) + Math.abs(zInput) / 3 <= controllerTolerance) {
      for (int i = 0; i < speeds.length; i++) {
        speeds[i] = 0;
        wheels[i].setSetpoint(wheels[i].getPIDInput()); // Brake
      }
    } else {
      for (int i = 0; i < wheels.length; i++) {
        SwerveResult calculated = wheels[i].calculate(xInput, yInput, zInput);
        wheels[i].setSetpoint(calculated.angle);
        speeds[i] = calculated.speed;
      }

      SwerveMath.capValuesSymmetrically(speeds, 1.0);
    }

    for (int i = 0; i < wheels.length; i++) {
      wheels[i].getDriveMotorConsumer().accept(speeds[i]);
    }
  }
  
  public void disable() {
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].setEnabled(false);
    }
  }

  public void enable() {
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].setEnabled(true);
    }
  }
}
