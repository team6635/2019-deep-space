package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {
  private final SpeedController topMotor = new WPI_VictorSPX(RobotMap.victorIntakeTop);
  private final SpeedController bottomMotor = new WPI_VictorSPX(RobotMap.victorIntakeBottom);

  public void setTop(double speed) {
    topMotor.set(speed);
  }

  public void setBottom(double speed) {
    bottomMotor.set(speed);
  }

  public void setBoth(double speed) {
    setTop(speed);
    setBottom(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
