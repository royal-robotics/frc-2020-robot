package frc.robot.subsystems.shooter;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.subsystems.RoyalSubsystem;

public class PitchingWheel extends RoyalSubsystem {
    private final CANSparkMax _motor;
    private final CANEncoder _encoder;
    private final CANPIDController _controller;
    private Double _targetRPM = null;

    public PitchingWheel() {
        _motor = Components.Shooter.shooterWheel;
        _encoder = _motor.getEncoder();

        final var CompositeRatio1 = 14.0 / 32.0;
        final var CompositeRatio2 = 56.0 / 18.0;
        final var GearRatio = CompositeRatio1 * CompositeRatio2;
        _encoder.setPositionConversionFactor(GearRatio);
        _encoder.setVelocityConversionFactor(GearRatio);

        _controller = _motor.getPIDController();
        _controller.setP(5e-4);
        _controller.setI(0e-6);
        _controller.setD(0);
        _controller.setIZone(0);
        _controller.setFeedbackDevice(_encoder);
        _controller.setFF(0.000137);
        _controller.setOutputRange(-1, 1);
        // _controller.
    }

    public void setPower(double power) {
        _targetRPM = null;
        _controller.setReference(power, ControlType.kDutyCycle);
    }

    public void setRPM(double rpm) {
        _targetRPM = rpm;
        _controller.setReference(rpm, ControlType.kVelocity);
    }

    public boolean onRPMTarget(double tolerance) {
        if (_targetRPM == null) {
            return false;
        }

        final var variation = _targetRPM * tolerance;
        return _encoder.getVelocity() >= (_targetRPM - variation) && _encoder.getVelocity() <= (_targetRPM + variation);
    }

    @Override
    public void periodic() {
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Shooter/PitchingWheel/Power", _motor.get());
        SmartDashboard.putNumber("Shooter/PitchingWheel/TargetRpm", _targetRPM == null ? -1 : _targetRPM);
        SmartDashboard.putNumber("Shooter/PitchingWheel/Rpm", _encoder.getVelocity());
        SmartDashboard.putBoolean("Shooter/PitchingWheel/AtTarget", this.onRPMTarget(0.05));
    }
}
