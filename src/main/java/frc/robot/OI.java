package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SetSucker;
import frc.robot.commands.SpitCargo;

public final class OI {
  public final Joystick leftStick = new Joystick(0);
  public final Joystick rightStick = new Joystick(1);

  public final JoystickButton triggerLeft = new JoystickButton(leftStick, 1);
  public final JoystickButton triggerRight = new JoystickButton(rightStick, 1);

  public OI() {
    triggerLeft.whileHeld(new SetSucker(-0.5));
    // triggerLeft.whenPressed(new SuckCargo(500));
    triggerRight.whenPressed(new SpitCargo(500));
  }
}