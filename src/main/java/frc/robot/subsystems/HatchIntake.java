package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;
import frc.robot.extensions.WPIUtils.AngleEncoder;

/**
 * Intake for hatch panels.
 */
public class HatchIntake extends PIDSubsystem {
  public final SpeedController hatcher = new WPI_VictorSPX(RobotMap.victorHatcher);
  public final AngleEncoder hatcherEncoder = WPIUtils.angleEncoderDCH(RobotMap.encoderHatcher, 11264);

  /**
   * Create a new HatchIntake.
   */
  public HatchIntake() {
    super("HatchIntake", 0.05, 0, 0);
    enable();
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return hatcherEncoder.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    hatcher.set(output);
  }
}
