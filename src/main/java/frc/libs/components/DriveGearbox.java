package frc.libs.components;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.*;

public class DriveGearbox {
    private final SpeedController _motor;
    private final EncoderGroup _encoder;
    private final boolean _inverted;

    public DriveGearbox(boolean inverted, CANSparkMax leader, CANSparkMax ...followers) {
        _motor = leader;
        _inverted = inverted;
        leader.setInverted(_inverted);

        for (var follower : followers) {
            follower.follow(leader);
        }

        // Setup encoders
        var encoders = new CANEncoder[followers.length + 1];
        encoders[0] = leader.getEncoder();
        for (var i = 0; i < followers.length; i++) {
            encoders[i + 1] = followers[i].getEncoder();
        }
        _encoder = new EncoderGroup(calculateFinalDriveRatio(), inverted, encoders);
    }

    public void setPower(double power) {
        _motor.set(power);
    }

    public void setVoltage(double voltage) {
        _motor.setVoltage(voltage);
    }

    public double getPosition() {
        return _encoder.getPosition();
    }

    public double getVelocity() {
        return _encoder.getVelocity();
    }

    public void reset() {
        _encoder.reset();
    }

    private static double calculateFinalDriveRatio() {
        final double CompositeRatio1 = 58 / 13;
        final double CompositeRatio2 = 34 / 22;
        final double CompositeRatio3 = 38 / 28;
        final double GearRatio = CompositeRatio1 * CompositeRatio2 * CompositeRatio3;

        final double WheelDiameter = 6.0;
        final double WheelCircumference = Math.PI * WheelDiameter;

        return GearRatio * WheelCircumference;
    }
}
