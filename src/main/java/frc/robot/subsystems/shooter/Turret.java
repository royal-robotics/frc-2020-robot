package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.*;
import frc.robot.subsystems.RoyalSubsystem;

public class Turret extends RoyalSubsystem {
    private final WPI_TalonSRX _motor;
    private final Encoder _encoder;
    private final TurretPidController _pidController;
    private final SimpleMotorFeedforward _feedforward;

    private final double PulsesPerRotation = 256.0;
    private final double PlatformCircumference = 40.84;
    private final double IdlerCircumference = 4.71;
    private final double DistancePerDegree = PlatformCircumference/360;

    private boolean _underPidControl = false;

    public Turret() {
        _motor = Components.Shooter.turret;
        _encoder = Components.Shooter.turretEncoder;
        _pidController = new TurretPidController();
        _feedforward = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);

        _encoder.setDistancePerPulse(IdlerCircumference / PulsesPerRotation);
    }

    public void stop() {
        _underPidControl = false;
        _motor.set(0.0);
    }

    public void setPower(double power) {
        _underPidControl = false;
        _motor.set(ControlMode.PercentOutput, power);
    }

    public void setTargetAngle(double targetAngle) {
        _underPidControl = true;
        _pidController.setSetpoint(targetAngle);
    }

    public boolean atTargetAngle() {
        return _pidController.atSetpoint();
    }

    public double getAngle() {
        return _encoder.getDistance() / DistancePerDegree;
    }

    public void reset() {
        _pidController.reset();
    }

    @Override
    public void periodic() {
        if (_underPidControl) {
            final var feed = _feedforward.calculate(getAngle() > _pidController.getSetpoint() ? -30.0 : 30.0);

            final var pidError = _pidController.calculate(getAngle());
            _motor.setVoltage(pidError + feed);
        }

        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Turret/Platform/Power", _motor.get());
        SmartDashboard.putNumber("Turret/Platform/Voltage", _motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Turret/Platform/Degrees", getAngle());
        SmartDashboard.putNumber("Turret/Platform/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Turret/Platform/OnTarget", _pidController.atSetpoint());
    }
}
