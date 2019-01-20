package frc.robot.extensions;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableImpl;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.extensions.Smath.Vector2;

public final class SwerveDrive extends RobotDriveBase {
  private static int instances;

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
    instances++;
    setName("SwerveDrive", instances);
    addChild(frontLeftWheel);
    addChild(frontRightWheel);
    addChild(backLeftWheel);
    addChild(backRightWheel);
  }

  public void swerveDrive(double x, double y, double z) {
    SwerveWheel[] wheels = getWheels();
    double[] speeds = new double[wheels.length];

    if (Smath.absAdd(x, y, z) <= 0.08) {
      for (SwerveWheel wheel : wheels) {
        wheel.stop();
      }
      return;
    }

    for (int i = 0; i < wheels.length; i++) {
      SwerveWheel wheel = wheels[i];
      wheel.enable();
      Vector2 r = new Vector2(wheel.getUnitVector());
      r.mul(z);
      r.add(x, y);
      double angle = r.getAngle();
      double speed = r.getMagnitude();
      wheel.setSetpoint(angle);
      speeds[i] = speed;
    }
    normalize(speeds);
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].getDriveMotor().set(ControlMode.PercentOutput, speeds[i]);
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

  public static final class SwerveWheel implements Sendable {
    private BaseMotorController driveMotor;
    private BaseMotorController pivotMotor;
    private Encoder pivotEncoder;
    private PIDController pid;
    private Vector2 unitVector;

    private SendableImpl sendableImpl;

    public SwerveWheel(BaseMotorController drive, BaseMotorController pivot, Encoder pivotEncoder, double xRel,
        double yRel, double p, double i, double d) {
      driveMotor = drive;
      pivotMotor = pivot;
      this.pivotEncoder = pivotEncoder;

      unitVector = new Vector2(-yRel, xRel);
      unitVector.div(unitVector.getMagnitude());

      pid = new PIDController(p, i, d, new PIDSource() {
        PIDSourceType sType;

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
          sType = pidSource;
        }

        @Override
        public double pidGet() {
          return getCurrentAngle();
        }

        @Override
        public PIDSourceType getPIDSourceType() {
          return sType;
        }
      }, new PIDOutput() {
        @Override
        public void pidWrite(double output) {
          usePIDOutput(output);
        }
      });

      // PID tuning
      pid.setAbsoluteTolerance(2);
      pid.setContinuous(true);
      pid.setInputRange(0, 360);
      pid.setOutputRange(-1, 1);

      sendableImpl = new SendableImpl(true);
    }

    public void setSetpoint(double setpoint) {
      pid.setSetpoint(setpoint);
    }

    public PIDController getPid() {
      return pid;
    }

    public Encoder getPivotEncoder() {
      return pivotEncoder;
    }

    public double getCurrentAngle() {
      return Smath.normalizeAngle(pivotEncoder.getDistance());
    }

    private void usePIDOutput(double output) {
      pivotMotor.set(ControlMode.PercentOutput, output);
    }

    public BaseMotorController getDriveMotor() {
      return driveMotor;
    }

    public BaseMotorController getPivotMotor() {
      return pivotMotor;
    }

    public Vector2 getUnitVector() {
      return unitVector;
    }

    @Override
    public String getName() {
      return sendableImpl.getName();
    }

    @Override
    public void setName(String name) {
      sendableImpl.setName(name);
    }

    @Override
    public String getSubsystem() {
      return sendableImpl.getSubsystem();
    }

    @Override
    public void setSubsystem(String subsystem) {
      sendableImpl.setSubsystem(subsystem);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("SwerveWheel");
      builder.setActuator(true);
      builder.addDoubleProperty("Pivot Encoder", pivotMotor::getSelectedSensorPosition, (double v) -> {
      });
      builder.addDoubleProperty("PID Setpoint", pid::getSetpoint, pid::setSetpoint);
    }

    protected final void addChild(Object child) {
      sendableImpl.addChild(child);
    }

    public void stop() {
      driveMotor.set(ControlMode.PercentOutput, 0);
      pid.setSetpoint(getCurrentAngle());
    }

    public void disable() {
      stop();
      pid.disable();
    }

    public void enable() {
      pid.enable();
    }
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("SwerveDrive");
    builder.setActuator(true);
  }

  @Override
  public void stopMotor() {

  }

  @Override
  public String getDescription() {
    return null;
  }
}
