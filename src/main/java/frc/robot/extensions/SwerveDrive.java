package frc.robot.extensions;

/**
 * This class handles four {@link SwerveWheel}s, and processes directional
 * inputs, calculating each wheel's optimal angle and speed to attain the
 * provided inputs.
 */
public final class SwerveDrive {
  /** The front left wheel. */
  SwerveWheel frontLeftWheel;
  /** The front right wheel. */
  SwerveWheel frontRightWheel;
  /** The back left wheel. */
  SwerveWheel backLeftWheel;
  /** The back right wheel. */
  SwerveWheel backRightWheel;

  /**
   * If false, the swerve drive and its wheels will not respond to drive calls.
   */
  boolean isEnabled = false;

  /**
   * Creates a new instance, accepting four wheels.
   * 
   * @param frontLeftWheel  the front left wheel.
   * @param frontRightWheel the front right wheel.
   * @param backLeftWheel   the back left wheel.
   * @param backRightWheel  the back right wheel.
   */
  public SwerveDrive(SwerveWheel frontLeftWheel, SwerveWheel frontRightWheel, SwerveWheel backLeftWheel,
      SwerveWheel backRightWheel) {
    this.frontLeftWheel = frontLeftWheel;
    this.frontRightWheel = frontRightWheel;
    this.backLeftWheel = backLeftWheel;
    this.backRightWheel = backRightWheel;
  }

  /**
   * Drives the robot using the provided inputs.
   * 
   * @param x
   * @param y
   * @param z
   */
  public void swerveDrive(double x, double y, double z) {
    // Ensure that the wheels all match the state of the instance.
    if (!isEnabled) {
      // Log a scary message.
      System.err.println("Tried to call SwerveDrive#swerveDrive in a disabled state!");
      disable();
      return;
    } else {
      enable();
    }

    // Store the wheels as an array, and create a speeds array of the same size.
    var wheels = getWheels();
    var speeds = new double[wheels.length];

    // If the absolute sum of the inputs is below a certain tolerance, then stop all
    // movement.
    // TODO: Move this to DriveRobotManual?
    if (Smath.absAdd(x, y, z) <= 0.08) {
      for (var wheel : wheels) {
        wheel.updateDriveMotorSpeed(0);
        wheel.updatePIDSetpoint(wheel.getCurrentEncoderValue());
      }
      return;
    }

    // Cauculate proper angle and speed for each wheel.
    for (int i = 0; i < wheels.length; i++) {
      var wheel = wheels[i];
      var r = wheel.getUTan();
      r.mul(z);
      r.add(x, y);
      var angle = r.getAngle();
      var speed = r.getMagnitude();

      // Reverse function in case it's shorter to go the other way around and reverse
      // the driving motor.
      var invertedAngle = Smath.normalizeAngle(angle + 180);
      var currentAngle = wheel.getCurrentEncoderValue();
      var regularError = Smath.calculateError(currentAngle, angle, 360);
      var invertedError = Smath.calculateError(currentAngle, invertedAngle, 360);
      if (Math.abs(invertedError) < Math.abs(regularError)) {
        speed = -speed;
        angle = invertedAngle;
      }

      wheel.updatePIDSetpoint(angle);
      speeds[i] = speed;
    }

    // Normalize the speeds that we have calculated, in case any are greater than 1,
    // then drive the wheels using those speeds.
    Smath.normalizeSpeeds(speeds, 1);
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].updateDriveMotorSpeed(speeds[i]);
    }
  }

  /**
   * Disables the swerve drive and its wheels.
   */
  public void disable() {
    isEnabled = false;
    for (SwerveWheel wheel : getWheels()) {
      wheel.disable();
    }
  }

  /**
   * Enables the swerve drive and its wheels.
   */
  public void enable() {
    isEnabled = true;
    for (SwerveWheel wheel : getWheels()) {
      wheel.enable();
    }
  }

  /**
   * Gets the wheels as an array.
   */
  private SwerveWheel[] getWheels() {
    SwerveWheel[] wheels = { frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel };
    return wheels;
  }
}
