package frc.libs.components;

import com.revrobotics.*;

public class EncoderGroup {
    private final CANEncoder[] _encoders;
    private final boolean _inverted;

    public EncoderGroup(double inchesPerTurn, boolean inverted, CANEncoder ...encoders) {
        _encoders = encoders;
        _inverted = inverted;

        for(var encoder : _encoders) {
            encoder.setPositionConversionFactor(inchesPerTurn);
            encoder.setVelocityConversionFactor(inchesPerTurn);
            encoder.setPosition(0.0);
        }
    }

    public double getPosition() {
        var sum = 0.0;
        for(var encoder : _encoders) {
            sum += encoder.getPosition();
        }
        final var position = sum / _encoders.length;
        return _inverted ? -position : position;
    }

    public double getVelocity() {
        var sum = 0.0;
        for(var encoder : _encoders) {
            sum += encoder.getVelocity();
        }
        final var velocity = sum / _encoders.length;
        return _inverted ? -velocity : velocity;
    }

    public void reset() {
        for(var encoder : _encoders) {
            encoder.setPosition(0.0);
        }
    }
}
