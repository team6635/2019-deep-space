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
  public static final int pSwervePivotBL = 21;
  public static final int pSwerveDriveBL = 6;
  public static final int encoderSwerveBL = 6; // and 7

  public static final int pSwerveDriveFR = 4;
  public static final int pSwervePivotFR = 22;
  public static final int encoderSwerveFR = 2; // and 3

  public static final int pSwerveDriveFL = 3;
  public static final int pSwervePivotFL = 24;
  public static final int encoderSwerveFL = 0; // and 1

  public static final int pSwervePivotBR = 23;
  public static final int pSwerveDriveBR = 2;
  public static final int encoderSwerveBR = 4; // and 5

  public static final int pTestMotor = 6;
}
