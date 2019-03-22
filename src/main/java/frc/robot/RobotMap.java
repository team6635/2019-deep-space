package frc.robot;

/**
 * Think of this class a a kind of phonebook of sorts. It stores the addresses
 * (port numbers) of every motor and actuator, and other I/O devices attached to
 * the RoboRIO, and allows you to access their address everywhere in the code by
 * just providing their name.
 * 
 * <p>
 * This class is also useful for other "magic numbers," used for example for
 * tuning and configuration. Some such numbers belong in their individual
 * subsystems, but others may affect robot-wide operation outside of just one
 * subsystem, and those are the ones which belong here.
 * 
 * <p>
 * This is useful because if you reference the same number in many places in
 * your code, you can simply change one value in {@code RobotMap} instead of
 * changing the number in many many places in the code.
 */
public final class RobotMap {
  public static final int victorElevator = 1;
  public static final int victorDriveBR = 2;
  public static final int victorDriveFL = 3;
  public static final int victorDriveFR = 4;
  public static final int victorClimberLiftBack = 5;
  public static final int victorDriveBL = 6;
  public static final int victorIntakeBottom = 7;
  public static final int victorClimberLiftFront = 8;
  public static final int victorClimberDriver = 9;
  public static final int victorIntakeTop = 10;
  public static final int victorHatcher = 11;

  public static final int encoderHatcher = 8;
  public static final int encoderClimberFront = 10;
  public static final int encoderClimberBack = 12;

  // Contants
  public static final double kClimberFrontExtended = -1811.5;
  public static final double kClimberBackExtended = 2043.25;
  public static final double kHatcherPosition = -556;
  public static final boolean useArcade = true;
}
