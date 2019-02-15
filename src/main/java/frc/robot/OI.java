package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class provides access to input devices (like joysticks) attached to the
 * Drive Console, and also handles events-- actions that run when a particular
 * button on a joystick is pressed, for example.
 */
public final class OI {
  // Our main controller for the robot. Notice that it is a public member, but not
  // a static member. You will need to use the instance of OI that exists on the
  // Robot in order to use these members.
  public final XboxController xbox = new XboxController(0);
  // Now we declare buttons on the joystick that we will bind to commands. When
  // the buttons are pressed, the command will run.
  final JoystickButton buttonA = new JoystickButton(xbox, 1);
  final JoystickButton buttonB = new JoystickButton(xbox, 2);

  public OI() {
  }
}
