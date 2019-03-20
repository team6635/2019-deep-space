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

  public ClimberBack() {
    super("ClimberBack", 0.015, 0.0, 0.0);

    setOutputRange(-1.0, 1.0);
    setAbsoluteTolerance(5);
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
    liftMotor.set(output * 11.0 / 16.0);
  }
}
