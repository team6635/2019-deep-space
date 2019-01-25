package frc.robot.extensions;

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

  /**
   * Creates a new quadrature (4x) <em>angle</em> encoder using the specified
   * channel, and the one above it.
   * 
   * @param chan1              the first channel to use, uses {@code chan1 + 1} as
   *                           the second.
   * @param ticksPerRevolution the ticks emitted by the encoder every full
   *                           revolution.
   * @return an {@link AngleEncoder} instance.
   */
  public static final AngleEncoder angleEncoderDCH(int chan1, double ticksPerRevolution) {
    var ae = new AngleEncoder(chan1, chan1 + 1, true, EncodingType.k4X, ticksPerRevolution);
    return ae;
  }

  /**
   * A wrapper around an {@link Encoder}, providing methods for calculating the
   * current angle of the encoder, as well as converting from ticks per revolution
   * that are not 360.
   */
  public static final class AngleEncoder extends Encoder {
    /** Number of pulses sent by the encoder per full revolution. */
    private final double pulsesPerRevolution;

    /**
     * Creates a new AngleEncoder with the specified parameters. See {@link Encoder}
     * for more information.
     * 
     * @param channelA            lower source channel (DIO).
     * @param channelB            higher source channel (DIO).
     * @param reverseDirection
     * @param encodingType
     * @param pulsesPerRevolution the amount of pulses sent by the encoder for every
     *                            full revolution.
     */
    public AngleEncoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType,
        double pulsesPerRevolution) {
      super(channelA, channelB, reverseDirection, encodingType);
      this.pulsesPerRevolution = pulsesPerRevolution;
    }

    /**
     * Creates a new AngleEncoder with the specified parameters. See {@link Encoder}
     * for more information. Assumes 360 pulses per revolution.
     * 
     * @param channelA         lower source channel (DIO).
     * @param channelB         higher source channel (DIO).
     * @param reverseDirection
     * @param encodingType
     */
    public AngleEncoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
      this(channelA, channelB, reverseDirection, encodingType, 360);
    }

    /**
     * <strong>DO NOT USE THIS METHOD. THIS WILL BREAK THE ANGLE
     * CALCULATIONS.</strong>
     * 
     * @throws Error This method should not be used.
     */
    @Override
    public void setDistancePerPulse(double distancePerPulse) throws Error {
      throw new Error("Attempted to call setDistancePerPulse on AngleEncoder.");
    }

    /**
     * Returns the current normalized angle of the encoder.
     */
    public double getAngle() {
      var raw = getDegrees();
      return Smath.normalizeAngle(raw);
    }

    /**
     * Returns the total distance traveled by the encoder in degrees.
     */
    public double getDegrees() {
      return getDistance() / pulsesPerRevolution * 360;
    }

    /**
     * Returns the distance traveled by the encoder in revolutions.
     */
    public double getRotations() {
      return getDistance() / pulsesPerRevolution;
    }
  }
}
