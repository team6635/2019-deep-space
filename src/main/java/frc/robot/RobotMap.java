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
  public static final int talonSwervePivotFL = 21;
  public static final int talonSwervePivotFR = 22;
  public static final int talonSwervePivotBL = 23;
  public static final int talonSwervePivotBR = 24;
  public static final int victorSwerveDriveFL = 1;
  public static final int victorSwerveDriveFR = 2;
  public static final int victorSwerveDriveBL = 3;
  public static final int victorSwerveDriveBR = 4;
  public static final int encoderSwerveFL = 0; // and 1
  public static final int encoderSwerveFR = 2; // and 3
  public static final int encoderSwerveBL = 4; // and 5
  public static final int encoderSwerveBR = 6; // and 7
}
