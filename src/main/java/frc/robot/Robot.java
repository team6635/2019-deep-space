package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.CargoHandler;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static OI oi;

  // public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  // Initialize subsystems.
  public static CargoHandler cargoHandler = new CargoHandler();
  public static DriveTrain driveTrain = new DriveTrain();

  @Override
  public void robotInit() {
    // Do not move this. Since the OI may use some commands, those commands might
    // get null pointers for any required subsystems if the OI is called during the
    // Robot class' construction.
    oi = new OI();

    // Start camera streams
    CameraServer.getInstance().startAutomaticCapture();
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
