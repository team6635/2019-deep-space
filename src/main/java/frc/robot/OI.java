package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SpitCargo;
import frc.robot.commands.SuckCargo;
import frc.robot.commands.SwitchDriveMethod;
import frc.robot.commands.TankDriveBot;

public final class OI {
  public final XboxController xbox = new XboxController(0);
  public final JoystickButton buttonA = new JoystickButton(xbox, 1);
  public final JoystickButton buttonB = new JoystickButton(xbox, 2);
  public final JoystickButton buttonX = new JoystickButton(xbox, 3);
  public final JoystickButton buttonY = new JoystickButton(xbox, 4);
  public final JoystickButton buttonLB = new JoystickButton(xbox, 5);
  public final JoystickButton buttonRB = new JoystickButton(xbox, 6);
  public final JoystickButton buttonCenterLeft = new JoystickButton(xbox, 7);

  public OI() {
    // buttonA.whileHeld(new TankDriveBot(1, 1));
    // buttonB.whileHeld(new TankDriveBot(-1, -1));
    buttonLB.whenPressed(new SuckCargo(500));
    buttonRB.whenPressed(new SpitCargo(500));
    buttonCenterLeft.whenPressed(new SwitchDriveMethod());
  }
}