package frc.robot.pid;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimedPID extends PID {
  protected final Timer timer = new Timer();
  protected boolean isEnabled = true;

  public TimedPID(double P, double I, double D, double tolerance, double modulus, long rate) {
    super(P, I, D, tolerance, modulus);

    timer.scheduleAtFixedRate(new TimerTask(){
      @Override
      public void run() {
        timerLoop();
      }
    }, rate, rate);
  }

  public abstract double getPIDInput();

  public abstract void usePIDOutput(double output);
  
  private void timerLoop() {
    if (!isEnabled) {
      return;
    }

    usePIDOutput(calculate(getPIDInput()));
  }

  public void setEnabled(boolean value) {
    isEnabled = value;
  }

  public void disable() {
    setEnabled(false);
  }

  public void enable() {
    setEnabled(true);
  }
}
