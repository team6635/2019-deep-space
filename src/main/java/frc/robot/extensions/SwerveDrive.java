package frc.robot.extensions;

public final class SwerveDrive {
  SwerveWheel frontLeftWheel;
  SwerveWheel frontRightWheel;
  SwerveWheel backLeftWheel;
  SwerveWheel backRightWheel;

  public SwerveDrive(SwerveWheel frontLeftWheel, SwerveWheel frontRightWheel, SwerveWheel backLeftWheel,
      SwerveWheel backRightWheel) {
    this.frontLeftWheel = frontLeftWheel;
    this.frontRightWheel = frontRightWheel;
    this.backLeftWheel = backLeftWheel;
    this.backRightWheel = backRightWheel;
  }

  public void swerveDrive(double x, double y, double z) {
    var wheels = getWheels();
    var speeds = new double[wheels.length];

    if (Smath.absAdd(x, y, z) <= 0.08) {
      for (SwerveWheel wheel : wheels) {
        wheel.updateDriveMotorSpeed(0);
        wheel.updatePIDSetpoint(wheel.getCurrentEncoderValue());
      }
      return;
    }

    for (int i = 0; i < wheels.length; i++) {
      SwerveWheel wheel = wheels[i];
      wheel.enable();
      var r = wheel.getUTan();
      r.mul(z);
      r.add(x, y);
      var angle = r.getAngle();
      var speed = r.getMagnitude();
      wheel.updatePIDSetpoint(angle);
      speeds[i] = speed;
    }

    Smath.normalizeSpeeds(speeds, 1);

    for (int i = 0; i < wheels.length; i++) {
      wheels[i].updateDriveMotorSpeed(speeds[i]);
    }
  }

  public void disable() {
    for (SwerveWheel wheel : getWheels()) {
      wheel.disable();
    }
  }

  public void enable() {
    for (SwerveWheel wheel : getWheels()) {
      wheel.enable();
    }
  }

  private SwerveWheel[] getWheels() {
    SwerveWheel[] wheels = { frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel };
    return wheels;
  }
}
