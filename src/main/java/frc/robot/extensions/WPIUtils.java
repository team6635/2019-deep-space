package frc.robot.extensions;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public final class WPIUtils {
  public static final Encoder neverestEncoderDCH(int chan1) {
    var e = new Encoder(chan1, chan1 + 1, true, EncodingType.k4X);
    // e.setDistancePerPulse(360 / 410);
    System.out.println("Distance per pulse: " + e.getDistancePerPulse());
    return e;
  }
}