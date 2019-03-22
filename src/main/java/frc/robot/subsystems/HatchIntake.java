package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Intake for hatch panels.
 */
public class HatchIntake extends Subsystem {
  public final SpeedController hatcher = new WPI_VictorSPX(RobotMap.victorElevator); // TODO: on Elevator because
                                                                                     // unused.

  /**
   * Create a new HatchIntake.
   */
  public HatchIntake() {
    super("HatchIntake");
    hatcher.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setPower(double speed) {
    hatcher.set(speed);
  }

  public void stop() {
    hatcher.set(0);
  }
}
