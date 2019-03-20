package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;

public class Elevator extends PIDSubsystem {
  private SpeedController motor = new WPI_VictorSPX(RobotMap.victorElevator);
  private Encoder encoder = WPIUtils.encoderDCH(RobotMap.encoderElevator);

  public Elevator() {
    super("Elevator", 0.015, 0.0, 0.0);
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return encoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    motor.set(output);
  }
}
