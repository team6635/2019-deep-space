package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;

public class ClimberBack extends PIDSubsystem {
  private SpeedController liftMotor = new WPI_VictorSPX(RobotMap.victorClimberLiftBack);
  private Encoder liftEncoder = WPIUtils.encoderDCH(RobotMap.encoderClimberBack);
  private SpeedController driveMotor = new WPI_VictorSPX(RobotMap.victorClimberDriver);

  private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.limitSwitchClimberBackBottom);
  // private DigitalInput topLimitSwitch = new
  // DigitalInput(RobotMap.limitSwitchClimberBackTop);

  public ClimberBack() {
    super("ClimberBack", 0.015, 0.0, 0.0);

    setOutputRange(-1.0, 1.0);
    setAbsoluteTolerance(0.01);

    enable();
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return liftEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Forward sends the climber down.
    if ((output > 0 && bottomLimitSwitch.get()) /* || (output < 0 && topLimitSwitch.get()) */) {
      liftMotor.stopMotor();
      return;
    }
    liftMotor.set(output / 4);
  }

  public void zoom(double speed) {
    driveMotor.set(speed / 4);
  }
}
