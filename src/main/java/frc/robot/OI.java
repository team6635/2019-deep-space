package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.commands.DriveRobot;

public class OI {
  private final XboxController controller = new XboxController(RobotMap.pController);
  private final JoystickButton testButton = new JoystickButton(controller, 1);

  public OI() {
    // SmartDashboard buttons
    SmartDashboard.putData("Auton", new Autonomous());

    // Physical Buttons
    testButton.whenPressed(new DriveRobot(0, 0, 1, 1000));
  }

  public XboxController getController() {
    return controller;
  }
}
