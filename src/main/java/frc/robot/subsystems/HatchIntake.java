package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.extensions.WPIUtils;

/**
 * Intake for hatch panels.
 */
public class HatchIntake extends PIDSubsystem {
  // public final SpeedController hatcher = new
  // WPI_VictorSPX(RobotMap.victorHatcher);
  public final SpeedController hatcher = new WPI_VictorSPX(RobotMap.victorHatcher);

  public final Encoder hatcherEncoder = WPIUtils.encoderDCH(RobotMap.encoderHatcher);

  // public final AngleEncoder hatcherEncoder =
  // WPIUtils.angleEncoderDCH(RobotMap.encoderHatcher, 2816);
  // public final Encoder hatcherEncoder = new Encoder(RobotMap.encoderHatcher,
  // RobotMap.encoderHatcher + 1);
  // public final Encoder hatcherEncoder =
  // WPIUtils.encoderDCH(RobotMap.encoderHatcher);

  /**
   * Create a new HatchIntake.
   */
  public HatchIntake() {
    super("HatchIntake", 0.0015, 0.0001, 0.01);
    hatcher.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return hatcherEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    hatcher.set(output / 2);
  }
}
