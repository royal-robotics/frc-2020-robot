package frc.libs.components;

import com.revrobotics.*;

public class EncoderGroup {
    private final CANEncoder[] _encoders;

    public EncoderGroup(double inchesPerTurn, boolean inverted, CANEncoder ...encoders) {
        _encoders = encoders;

        for(var encoder : _encoders) {
            encoder.setPositionConversionFactor(inchesPerTurn);
            encoder.setVelocityConversionFactor(inchesPerTurn);
            encoder.setInverted(inverted);
            encoder.setPosition(0.0);
        }
    }

    public double getPosition() {
        var sum = 0.0;
        for(var encoder : _encoders) {
            sum += encoder.getPosition();
        }
        return sum / _encoders.length;
    }

    public double getVelocity() {
        var sum = 0.0;
        for(var encoder : _encoders) {
            sum += encoder.getVelocity();
        }
        return sum / _encoders.length;
    }

    public void reset() {
        for(var encoder : _encoders) {
            encoder.setPosition(0.0);
        }
    }
}
