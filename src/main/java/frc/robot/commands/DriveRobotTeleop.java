package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveRobotTeleop extends TimedCommand {
  public DriveRobotTeleop(DoubleSupplier xInput, DoubleSupplier yInput, DoubleSupplier zInput, double time) {
    super(time / 1000);
    requires(Robot.drivetrain);
    Robot.drivetrain.drive(xInput.getAsDouble(), yInput.getAsDouble(), zInput.getAsDouble());
  }

  public DriveRobotTeleop(XboxController controller, double time) {
    this(() -> controller.getX(Hand.kRight), () -> controller.getY(Hand.kRight), () -> controller.getX(Hand.kLeft),
        time);
  }
}
