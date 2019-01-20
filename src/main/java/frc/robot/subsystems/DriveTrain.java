package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotManual;
import frc.robot.extensions.Smath;
import frc.robot.extensions.SwerveDrive;
import frc.robot.extensions.SwerveDrive.SwerveWheel;

// The PID is used for double-field-centric
// control of the robot. It will constantly
// attempt to rotate the robot to the current
// setpoint.
public class DriveTrain extends PIDSubsystem {
  private static final double Kp = 0.015, Ki = 0.001, Kd = 0.003, KwheelDistanceX = 20, KwheelDistanceY = 22;

  AnalogGyro gyro = new AnalogGyro(0);
  SwerveWheel frontLeftWheel = new SwerveWheel(new TalonSRX(RobotMap.victorSwerveDriveFL),
      new VictorSPX(RobotMap.victorSwerveDriveFL),
      new Encoder(RobotMap.encoderSwerveFL, RobotMap.encoderSwerveFL + 1, true, EncodingType.k4X), -KwheelDistanceX / 2,
      KwheelDistanceY / 2, Kp, Ki, Kd);
  SwerveWheel frontRightWheel = new SwerveWheel(new TalonSRX(RobotMap.victorSwerveDriveFR),
      new VictorSPX(RobotMap.victorSwerveDriveFR),
      new Encoder(RobotMap.encoderSwerveFR, RobotMap.encoderSwerveFR + 1, true, EncodingType.k4X), KwheelDistanceX / 2,
      KwheelDistanceY / 2, Kp, Ki, Kd);
  SwerveWheel backLeftWheel = new SwerveWheel(new TalonSRX(RobotMap.victorSwerveDriveBL),
      new VictorSPX(RobotMap.victorSwerveDriveBL),
      new Encoder(RobotMap.encoderSwerveBL, RobotMap.encoderSwerveBL + 1, true, EncodingType.k4X), -KwheelDistanceX / 2,
      -KwheelDistanceY / 2, Kp, Ki, Kd);
  SwerveWheel backRightWheel = new SwerveWheel(new TalonSRX(RobotMap.victorSwerveDriveBR),
      new VictorSPX(RobotMap.victorSwerveDriveBR),
      new Encoder(RobotMap.encoderSwerveBR, RobotMap.encoderSwerveBR + 1, true, EncodingType.k4X), KwheelDistanceX / 2,
      -KwheelDistanceY / 2, Kp, Ki, Kd);

  SwerveDrive swerveDrive = new SwerveDrive(frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel);

  private double z = 0;

  public DriveTrain() {
    super("DriveTrain", 1, 2, 3);
    // Set up field-centric PID
    getPIDController().setInputRange(0, 360);
    getPIDController().setOutputRange(-1, 1);
    getPIDController().setAbsoluteTolerance(2);
    getPIDController().setContinuous(true);

    // Add LiveWindow values
    addChild("Gyro", gyro);
    addChild(frontLeftWheel);
    addChild(frontRightWheel);
    addChild(backLeftWheel);
    addChild(backRightWheel);
  }

  public double getHeading() {
    return Smath.normalizeAngle(gyro.getAngle());
  }

  public void goToHeading(double target) {
    setSetpoint(Smath.normalizeAngle(target));
  }

  public void swerveDrive(double x, double y) {
    swerveDrive.swerveDrive(x, y, z);
  }

  public void swerveDrive(double x, double y, double targetAngle) {
    goToHeading(targetAngle);
    swerveDrive.swerveDrive(x, y, z);
  }

  @Override
  public void disable() {
    super.disable();
    swerveDrive.disable();
  }

  @Override
  public void enable() {
    super.enable();
    swerveDrive.enable();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveRobotManual());
  }

  @Override
  protected double returnPIDInput() {
    return getHeading();
  }

  @Override
  protected void usePIDOutput(double output) {
    z = output;
  }
}
