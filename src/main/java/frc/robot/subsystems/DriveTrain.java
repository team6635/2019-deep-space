package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveRobotTeleop;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public class DriveTrain extends Subsystem {
  private final AnalogGyro gyro = new AnalogGyro(RobotMap.pGyro);

  private final BaseMotorController motorDriveFL = new VictorSPX(RobotMap.pMotorDriveFrontLeft);
  private final BaseMotorController motorPivotFL = new VictorSPX(RobotMap.pMotorPivotFrontLeft);
  private final BaseMotorController motorDriveRL = new VictorSPX(RobotMap.pMotorDriveRearLeft);
  private final BaseMotorController motorPivotRL = new VictorSPX(RobotMap.pMotorPivotRearLeft);
  private final BaseMotorController motorDriveFR = new VictorSPX(RobotMap.pMotorDriveFrontRight);
  private final BaseMotorController motorPivotFR = new TalonSRX(RobotMap.pMotorPivotFrontRight);
  private final BaseMotorController motorDriveRR = new VictorSPX(RobotMap.pMotorDriveRearRight);
  private final BaseMotorController motorPivotRR = new VictorSPX(RobotMap.pMotorPivotRearRight);

  private final Encoder encoderFL = new Encoder(RobotMap.pEncoderFrontLeft[0], RobotMap.pEncoderFrontLeft[1]);
  private final Encoder encoderRL = new Encoder(RobotMap.pEncoderRearLeft[0], RobotMap.pEncoderRearLeft[1]);
  private final Encoder encoderFR = new Encoder(RobotMap.pEncoderFrontRight[0], RobotMap.pEncoderFrontRight[1]);
  private final Encoder encoderRR = new Encoder(RobotMap.pEncoderRearRight[0], RobotMap.pEncoderRearRight[1]);

  private final Wheel wheelFL = new Wheel(motorDriveFL, motorPivotFL, encoderFL, -10.5, 11);
  private final Wheel wheelRL = new Wheel(motorDriveRL, motorPivotRL, encoderRL, -10.5, -11);
  private final Wheel wheelFR = new Wheel(motorDriveFR, motorPivotFR, 10.5, 11);
  private final Wheel wheelRR = new Wheel(motorDriveRR, motorPivotRR, encoderRR, 10.5, -11);

  private final Wheel[] wheels = { wheelFL, wheelRL, wheelFR, wheelRR };
  private final SwerveDrive swerveDrive = new SwerveDrive(wheels);

  public DriveTrain() {
    super();
    addChild("Encoder FL", encoderFL);
    addChild("Encoder FR", encoderFR);
    addChild("Encoder RL", encoderRL);
    addChild("Encoder RR", encoderRR);
  }

  @Override
  protected void initDefaultCommand() {
    // TODO: Add default command for joystick driving
    // setDefaultCommand(command);
    setDefaultCommand(new DriveRobotTeleop(Robot.oi.getController(), 60 * 2.75 * 1000));
  }

  public void log() {
    // TODO: SmartDashboard logging
  }

  public void drive(double x, double y, double z) {
    enable();
    swerveDrive.drive(x, y, z);
  }

  public void stop() {
    swerveDrive.drive(0, 0, 0);
  }

  public void disable() {
    stop();
    swerveDrive.disable();
  }

  public void enable() {
    stop();
    swerveDrive.enable();
  }

  public double getHeading() {
    return gyro.getAngle();
  }

  public void reset() {
    gyro.reset();
    encoderFL.reset();
    encoderFR.reset();
    encoderRL.reset();
    encoderRR.reset();
  }

  private static final class Wheel extends SwerveWheel {
    public Wheel(BaseMotorController drive, BaseMotorController pivot, Encoder pivotEncoder, double x, double y) {
      super((double speed) -> drive.set(ControlMode.PercentOutput, speed), () -> pivotEncoder.getDistance(),
          (double pSpeed) -> pivot.set(ControlMode.PercentOutput, pSpeed), x, y);
    }

    public Wheel(BaseMotorController drive, BaseMotorController talon, double x, double y) {
      super((double speed) -> drive.set(ControlMode.PercentOutput, speed), () -> talon.getSelectedSensorPosition(),
          (double pSpeed) -> talon.set(ControlMode.PercentOutput, pSpeed), x, y);
    }
  }
}
