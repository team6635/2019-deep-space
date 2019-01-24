package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This subsystem is used for testing a single motor for operation. It uses the
 * A and B buttons on the controller to control the motor.
 */
public class Tester extends Subsystem {
  BaseMotorController motor = new VictorSPX(RobotMap.victorTestMotor);

  @Override
  public void initDefaultCommand() {
    // This should have no default command.
  }

  /**
   * Turns the motor with a specified speed.
   * 
   * @param speed the speed to turn the motor at.
   */
  public void moveMotor(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Stops the motor.
   */
  public void stop() {
    motor.set(ControlMode.PercentOutput, 0);
  }
}