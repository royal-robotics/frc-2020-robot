package frc.robot.subsystems.drivebase;

import frc.libs.components.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMax.*;

public class DriveGearbox {
    private final CANSparkMax _motor;
    private final CANSparkMax[] _followers;
    private final EncoderGroup _encoder;
    private final boolean _inverted;

    public DriveGearbox(boolean inverted, CANSparkMax leader, CANSparkMax ...followers) {
        _motor = leader;
        _followers = followers;
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
        _encoder = new EncoderGroup(calculateFinalDriveRatio(), false, encoders);
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

    public void setBreakMode(boolean breakModeOn) {
        final var mode = breakModeOn ? IdleMode.kBrake : IdleMode.kCoast;
        _motor.setIdleMode(mode);
        for (var i = 0; i < _followers.length; i++) {
            _followers[i].setIdleMode(mode);
        }
    }

    public void reset() {
        _encoder.reset();
    }

    private static double calculateFinalDriveRatio() {
        final double CompositeRatio1 = 13.0 / 58.0;
        final double CompositeRatio2 = 22.0 / 34.0;
        final double CompositeRatio3 = 28.0 / 38.0;
        final double GearRatio = CompositeRatio1 * CompositeRatio2 * CompositeRatio3;

        final double WheelDiameter = 6.0 * 0.0254;
        final double WheelCircumference = Math.PI * WheelDiameter;

        final double WheelFudgeFactor = 0.9599 * 1.027;
        return GearRatio * WheelCircumference * WheelFudgeFactor;
    }
}
