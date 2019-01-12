package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.swervedrive.SwerveDrive;
import frc.robot.swervedrive.SwerveWheel;

public class DriveTrain extends Subsystem {
  private final AnalogGyro gyro = new AnalogGyro(0);

  private final BaseMotorController motorDriveFL = new VictorSPX(1);
  private final BaseMotorController motorPivotFL = new VictorSPX(2);
  private final BaseMotorController motorDriveRL = new VictorSPX(3);
  private final BaseMotorController motorPivotRL = new VictorSPX(4);
  private final BaseMotorController motorDriveFR = new VictorSPX(5);
  private final BaseMotorController motorPivotFR = new VictorSPX(6);
  private final BaseMotorController motorDriveRR = new VictorSPX(7);
  private final BaseMotorController motorPivotRR = new VictorSPX(8);

  private final Encoder encoderFL = new Encoder(0, 1);
  private final Encoder encoderRL = new Encoder(2, 3);
  private final Encoder encoderFR = new Encoder(4, 5);
  private final Encoder encoderRR = new Encoder(6, 7);

  private final Wheel wheelFL = new Wheel(motorDriveFL, motorPivotFL, encoderFL, -10.5, 11);
  private final Wheel wheelRL = new Wheel(motorDriveRL, motorPivotRL, encoderRL, -10.5, -11);
  private final Wheel wheelFR = new Wheel(motorDriveFR, motorPivotFR, encoderFR, 10.5, 11);
  private final Wheel wheelRR = new Wheel(motorDriveRR, motorPivotRR, encoderRR, 10.5, -11);

  private final Wheel[] wheels = { wheelFL, wheelRL, wheelFR, wheelRR };
  private final SwerveDrive swerveDrive = new SwerveDrive(wheels, gyro);

  public DriveTrain() {
    super();
  }

  @Override
  protected void initDefaultCommand() {
    // TODO: Add default command for joystick driving
    // setDefaultCommand(command);
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
  }
}
