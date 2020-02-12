package frc.libs.components;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;

public class DriveGearbox2019
{
    private final SpeedController _motor;
    private final Encoder _encoder;

    public DriveGearbox2019(WPI_TalonSRX leader, Encoder encoder, WPI_VictorSPX ...followers)
    {
        _motor = leader;
        _encoder = encoder;

        for(int x = 0; x < followers.length; x++)
        {
            followers[x].follow(leader);
        }
    }

    public void setPower(double power) {
        _motor.set(power);
    }

    public void setVoltage(double voltage) {
        _motor.setVoltage(voltage);
    }

    public double getPosition() {
        return _encoder.getDistance();
    }

    public double getWheelSpeed() {
        return _encoder.getRate();
    }

    public void resetPosition(double distancePerPulse, boolean reversedDirection) {
        _encoder.setDistancePerPulse(distancePerPulse);
        _encoder.setReverseDirection(reversedDirection);

        _encoder.reset();
    }
}
