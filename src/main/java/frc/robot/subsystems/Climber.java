package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {
  private SpeedController motorLiftBack = new WPI_VictorSPX(RobotMap.victorClimberLiftBack);
  private SpeedController motorLiftFront = new WPI_VictorSPX(RobotMap.victorClimberLiftFront);
  private SpeedController motorDriver = new WPI_VictorSPX(RobotMap.victorClimberDriver);

  public void climbBack(double speed) {
    motorLiftBack.set(speed);
  }

  public void climbFront(double speed) {
    motorLiftFront.set(speed);
  }

  public void drive(double speed) {
    motorDriver.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
