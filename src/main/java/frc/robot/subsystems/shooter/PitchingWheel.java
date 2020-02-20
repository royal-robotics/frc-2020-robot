package frc.robot.subsystems.shooter;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.subsystems.RoyalSubsystem;

public class PitchingWheel extends RoyalSubsystem {
    private final CANSparkMax _motor;
    private final CANEncoder _encoder;

    public PitchingWheel() {
        _motor = Components.Shooter.shooterWheel;
        _encoder = _motor.getEncoder();

        final var CompositeRatio1 = 16.0 / 30.0;
        final var CompositeRatio2 = 56.0 / 18.0;
        final var GearRatio = CompositeRatio1 * CompositeRatio2;
        _encoder.setPositionConversionFactor(GearRatio);
        _encoder.setVelocityConversionFactor(GearRatio);
    }

    public void setPower(double power) {
        _motor.set(power);
    }

    @Override
    public void periodic() {
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Shooter/PitchingWheel/Power", _motor.get());
        SmartDashboard.putNumber("Shooter/PitchingWheel/Velocity", _encoder.getVelocity());
    }
}
