package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;

public class ClimberBack extends PIDSubsystem {
  private SpeedController liftMotor = new WPI_VictorSPX(RobotMap.victorClimberLiftBack);
  private Encoder liftEncoder = WPIUtils.encoderDCH(RobotMap.encoderClimberBack);
  private SpeedController driveMotor = new WPI_VictorSPX(RobotMap.victorClimberDriver);

  public ClimberBack() {
    super("ClimberBack", 0.015, 0.0, 0.0);

    setAbsoluteTolerance(3);
    setOutputRange(-1.0, 1.0);

    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return liftEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    // liftMotor.set(output);
  }

  public void zoom(double speed) {
    driveMotor.set(speed / 4);
  }
}
