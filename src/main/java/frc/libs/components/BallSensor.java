package frc.libs.components;

import edu.wpi.first.wpilibj.DigitalInput;

public class BallSensor
{
    private final DigitalInput sensor;

    public BallSensor(int channel)
    {
        sensor = new DigitalInput(channel);
    }

    public Boolean isBallDetected()
    {
        return sensor.get();
    }
}
