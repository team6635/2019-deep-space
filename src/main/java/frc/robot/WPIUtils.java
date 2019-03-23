package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * Small helper utilities for repetitive WPILib tasks.
 */
public final class WPIUtils {
  /**
   * Creates a new quadrature (4x) Encoder using the specified channel, and the
   * one above it.
   * 
   * @param chan1 the first channel to use, uses {@code chan1 + 1} as the second.
   * @return the created {@link Encoder}.
   */
  public static final Encoder encoderDCH(int chan1) {
    var e = new Encoder(chan1, chan1 + 1, true, EncodingType.k4X);
    return e;
  }
}
