package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

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
}
