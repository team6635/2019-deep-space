package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
  private final XboxController controller = RobotMap.controller;

  public OI() {
    // SmartDashboard buttons
    // SmartDashboard.putData("Command Name", command);

    // Buttons
    final JoystickButton testButton = new JoystickButton(controller, 1);

    // testButton.whenPressed(command);
  }

  public XboxController getController() {
    return controller;
  }
}
