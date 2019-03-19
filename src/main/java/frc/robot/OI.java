package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveClimber;
import frc.robot.commands.EndgameClimb;
import frc.robot.commands.MoveClimberBack;
import frc.robot.commands.MoveClimberFront;

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
  // public final XboxController xbox2 = new XboxController(1);
  // Now we declare buttons on the joystick that we will bind to commands. When
  // the buttons are pressed, the command will run.
  final JoystickButton buttonA1 = new JoystickButton(xbox, 1);
  final JoystickButton buttonB1 = new JoystickButton(xbox, 2);
  final JoystickButton buttonX1 = new JoystickButton(xbox, 3);
  final JoystickButton buttonY1 = new JoystickButton(xbox, 4);

  final JoystickButton buttonLB1 = new JoystickButton(xbox, 5);
  final JoystickButton buttonRB1 = new JoystickButton(xbox, 6);

  final JoystickButton buttonBack1 = new JoystickButton(xbox, 7);
  final JoystickButton buttonStart1 = new JoystickButton(xbox, 8);

  public OI() {
    buttonA1.whenPressed(new MoveClimberFront(0));
    buttonB1.toggleWhenPressed(new MoveClimberFront(-3623.0));

    buttonX1.toggleWhenPressed(new MoveClimberBack(0));
    buttonY1.toggleWhenPressed(new MoveClimberBack(4085.75));

    buttonLB1.whileHeld(new DriveClimber(1));
    buttonRB1.whileHeld(new DriveClimber(-1));

    buttonStart1.whenPressed(new EndgameClimb());
  }
}
