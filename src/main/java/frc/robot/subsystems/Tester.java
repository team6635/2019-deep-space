package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This subsystem is used for testing a single motor for operation. It uses the
 * A and B buttons on the controller to control the motor.
 */
public class Tester extends Subsystem {
  SpeedController motor = new WPI_VictorSPX(RobotMap.pTestMotor);

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
    motor.set(speed);
  }

  /**
   * Stops the motor.
   */
  public void stop() {
    motor.set(0);
  }
}
