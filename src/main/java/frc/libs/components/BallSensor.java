package frc.libs.components;

import edu.wpi.first.wpilibj.DigitalInput;

public class BallSensor {
    private final DigitalInput _sensor;

    public BallSensor(int channel) {
        // TODO: DigitalInput should be passed in from static Components class.
        _sensor = new DigitalInput(channel);
    }

    public Boolean isBallDetected() {
        return _sensor.get();
    }
}
