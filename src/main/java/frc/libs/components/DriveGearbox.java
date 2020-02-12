package frc.libs.components;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.*;

public class DriveGearbox {
    private final SpeedController _motor;

    public DriveGearbox(CANSparkMax leader, CANSparkMax ...followers)
    {
        _motor = leader;

        for (var follower : followers) {
            follower.follow(leader);
        }
    }

    public void setPower(double power) {
        _motor.set(power);
    }

    public void setVoltage(double voltage) {
        _motor.setVoltage(voltage);
    }

    public double getPosition() {
        // TODO: make a royal encoder that takes all the CanSparkMaxs and averages their readings.
        // return _encoder.getDistance();
        return 0.0;
    }

    public double getWheelSpeed() {
        // return _encoder.getRate();
        return 0.0;
    }

    public void resetPosition(double distancePerPulse, boolean reversedDirection) {
        // _encoder.setDistancePerPulse(distancePerPulse);
        // _encoder.setReverseDirection(reversedDirection);

        // _encoder.reset();
    }
}
