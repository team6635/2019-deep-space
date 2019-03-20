package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Elevator extends PIDSubsystem {
  // private SpeedController motor = new WPI_VictorSPX(RobotMap.victorElevator);
  // private Encoder encoder = WPIUtils.encoderDCH(RobotMap.encoderElevator);

  public Elevator() {
    super("Elevator", 0.015, 0.0, 0.0);
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    // return encoder.getDistance();
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    // motor.set(output);
  }
}
