package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.DriveClimber;
import frc.robot.commands.MoveHatcher;

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
  final JoystickButton button_A = new JoystickButton(xbox, 1);
  final JoystickButton button_B = new JoystickButton(xbox, 2);
  final JoystickButton button_X = new JoystickButton(xbox, 3);
  final JoystickButton button_Y = new JoystickButton(xbox, 4);
  final JoystickButton button_LB = new JoystickButton(xbox, 5);
  final JoystickButton button_RB = new JoystickButton(xbox, 6);
  final JoystickButton button_Center_Left = new JoystickButton(xbox, 7);
  final JoystickButton button_Center_Right = new JoystickButton(xbox, 8);

  public OI() {
    button_A.whenPressed(new MoveHatcher(-556));

    button_LB.whileHeld(new DriveClimber(1));
    button_RB.whileHeld(new DriveClimber(-1));

    button_Center_Left.whenPressed(new ClimbUp());
  }
}
