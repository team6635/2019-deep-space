package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberBackDriver extends Subsystem {
  private WPI_VictorSPX motor = new WPI_VictorSPX(RobotMap.victorClimberDriver);

  public void setSpeed(double speed) {
    motor.set(speed);
  }

  public void stop() {
    motor.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
  }
}
