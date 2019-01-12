package frc.robot.swervedrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.swervedrive.SwerveMath.Point2;

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

    if ((Math.abs(xInput) + Math.abs(yInput) + Math.abs(zInput)) / 3 <= controllerTolerance) {
      for (int i = 0; i < wheels.length; i++) {
        wheels[i].brake();
      }
    } else {
      for (int i = 0; i < wheels.length; i++) {
        SwerveWheel wheel = wheels[i];
        Point2 r = Point2.multiply(wheel.getUnitTangent(), zInput);
        Point2 result = Point2.add(r, new Point2(xInput, yInput));

        double angle = Math.atan2(result.y, result.x) * (180 / Math.PI) - 90 % 360;
        double speed = result.magnitude();

        // TODO Reverse function

        wheel.setSetpoint(angle);
        speeds[i] = speed;
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
