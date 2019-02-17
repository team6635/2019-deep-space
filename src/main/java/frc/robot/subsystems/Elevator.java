package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;

public class Elevator extends PIDSubsystem {
  private SpeedController motor = new WPI_VictorSPX(RobotMap.victorElevator);
  // private Encoder encoder = WPIUtils.encoderDCH(RobotMap.encoderElevator);

  public Elevator() {
    super("Elevator", 0.015, 0.0, 0.0);
    enable();
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    // return encoder.getDistance();
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    motor.set(output);
  }
}
