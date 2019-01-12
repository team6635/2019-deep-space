package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public final class RobotMap {
  // Port numbers
  private static final int pController = 0;
  private static final int pGyro = 0;

  private static final int pMotorDriveFrontLeft = 1;
  private static final int pMotorPivotFrontLeft = 2;

  private static final int pMotorDriveFrontRight = 3;
  private static final int pMotorPivotFrontRight = 4;

  private static final int pMotorDriveRearLeft = 5;
  private static final int pMotorPivotRearLeft = 6;

  private static final int pMotorDriveRearRight = 7;
  private static final int pMotorPivotRearRight = 8;

  private static final int[] pEncoderFrontLeft = {0, 1};
  private static final int[] pEncoderFrontRight = {2, 3};
  private static final int[] pEncoderRearRight = {5, 4};
  private static final int[] pEncoderRearLeft = {6, 7};


  // Inputs
  public static final XboxController controller = new XboxController(pController);

  // Non-wheel actuators and devices
  public static final AnalogGyro gyro = new AnalogGyro(pGyro);

  // Front left wheel
  public static final VictorSPX motorPivotFrontLeft = new VictorSPX(pMotorPivotFrontLeft);
  public static final VictorSPX motorDriveFrontLeft = new VictorSPX(pMotorDriveFrontLeft);
  public static final Encoder encoderPivotFrontLeft = new Encoder(pEncoderFrontLeft[0], pEncoderFrontLeft[1], true, EncodingType.k4X);
  public static final CustomSwerveWheel<VictorSPX> wheelFrontLeft =
    new CustomSwerveWheel<VictorSPX>(motorDriveFrontLeft, motorPivotFrontLeft, encoderPivotFrontLeft, -10.5, 11);

  // Front right wheel
  public static final VictorSPX motorPivotFrontRight = new VictorSPX(pMotorPivotFrontRight);
  public static final VictorSPX motorDriveFrontRight = new VictorSPX(pMotorDriveFrontRight);
  public static final Encoder encoderPivotFrontRight = new Encoder(pEncoderFrontRight[0], pEncoderFrontRight[1], true, EncodingType.k4X);
  public static final CustomSwerveWheel<VictorSPX> wheelFrontRight =
    new CustomSwerveWheel<VictorSPX>(motorDriveFrontRight, motorPivotFrontRight, encoderPivotFrontRight, 10.5, 11);

  // Rear right wheel
  public static final VictorSPX motorPivotRearRight = new VictorSPX(pMotorPivotRearRight);
  public static final VictorSPX motorDriveRearRight = new VictorSPX(pMotorDriveRearRight);
  public static final Encoder encoderPivotRearRight = new Encoder(pEncoderRearRight[0], pEncoderRearRight[1], true, EncodingType.k4X);
  public static final CustomSwerveWheel<VictorSPX> wheelRearRight =
    new CustomSwerveWheel<VictorSPX>(motorDriveRearRight, motorPivotRearRight, encoderPivotRearRight, 10.5, -11);
  
  // Rear left wheel
  public static final VictorSPX motorPivotRearLeft = new VictorSPX(pMotorPivotRearLeft);
  public static final VictorSPX motorDriveRearLeft = new VictorSPX(pMotorDriveRearLeft);
  public static final Encoder encoderPivotRearLeft = new Encoder(pEncoderRearLeft[0], pEncoderFrontLeft[1], true, EncodingType.k4X);
  public static final CustomSwerveWheel<VictorSPX> wheelRearLeft =
    new CustomSwerveWheel<VictorSPX>(motorDriveRearLeft, motorPivotRearLeft, encoderPivotRearLeft, -10.5, -11);

  public static final SwerveWheel[] wheels = {
    wheelFrontLeft,
    wheelFrontRight,
    wheelRearRight,
    wheelRearLeft,
  };

  public static final SwerveDrive swerveDrive = new SwerveDrive(wheels, gyro);

  public static final class CustomSwerveWheel <T extends BaseMotorController> extends SwerveWheel {
    public CustomSwerveWheel(T drive, T pivot, Encoder pivotEncoder, double x, double y) {
      super(
        (double speed) -> drive.set(ControlMode.PercentOutput, speed),
        () -> pivotEncoder.getDistance(),
        (double pSpeed) -> pivot.set(ControlMode.PercentOutput, pSpeed), 
        x, y
      );
    }
  }
}
