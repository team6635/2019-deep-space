package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoHandler extends Subsystem {
  private WPI_VictorSPX motor = new WPI_VictorSPX(RobotMap.victorSPX_CargoMotor);

  /**
   * Negative sucks, positive spits.
   * 
   * @param speed the speed to set the motor to.
   */
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
