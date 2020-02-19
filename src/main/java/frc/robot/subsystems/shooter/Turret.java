package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.libs.components.*;
import frc.libs.subsystems.*;
import frc.libs.utils.*;
import frc.robot.*;

public class Turret extends PositionConstrainedSubsystem {
    private final WPI_TalonSRX _motor;
    private final Encoder _encoder;
    private final DigitalInput _leftLimit;
    private final DigitalInput _rightLimit;

    private Double _lastStoredMeasure = null;

    private static final String positionSettingName = "turret-position-v5";

    public Turret() {
        super(new TurretPidController(), Settings.loadDouble(positionSettingName, 0.0), -82.0, 82.0, 3.0);
        _motor = Components.Shooter.turret;
        _encoder = Components.Shooter.turretEncoder;
        _leftLimit = Components.Shooter.leftLimit;
        _rightLimit = Components.Shooter.rightlimit;

        final var PulsesPerRotation = 256.0;
        final var IdlerCircumference  = 4.71;
        final var TurretCircumference = 40.84;
        final var DistancePerPulse = IdlerCircumference / PulsesPerRotation;
        final var DistancePerDegree = TurretCircumference / 360.0;
        final var DegreesPerPulse = DistancePerPulse / DistancePerDegree;
        _encoder.setDistancePerPulse(DegreesPerPulse);
    }

    public void setPower(double power) {
        if (isEnabled()) {
            disable();
        }

        power = clampOnConstraints(power);
        updateSave(power);
        _motor.set(power);
    }

    @Override
    protected void setOutput(double pidError, double setpoint) {
        final var feedforwardHelper = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);
        final var feedforwardVelocity = getMeasurement() < setpoint ? 30.0 : -30.0;
        final var feedforward = feedforwardHelper.calculate(feedforwardVelocity);

        var voltage = clampOnConstraints(feedforward + pidError);
        updateSave(voltage);
        _motor.setVoltage(voltage);
    }

    private void updateSave(double power) {
        if (power == 0.0) {
            final var measure = getMeasurement();
            if (_lastStoredMeasure != null && Math.abs(_lastStoredMeasure - measure) > 0.1) {
                save();
                _lastStoredMeasure = measure;
            }
        }
    }

    @Override
    protected double getRelativeMeasurement() {
        return _encoder.getDistance();
    }

    public void save() {
        Settings.save(positionSettingName, getMeasurement());
    }

    @Override
    public void periodic() {
        super.periodic();
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Shooter/Turret/Power", _motor.get());
        SmartDashboard.putNumber("Shooter/Turret/Voltage", _motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Shooter/Turret/Degrees", getMeasurement());
        SmartDashboard.putNumber("Shooter/Turret/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Shooter/Turret/OnTarget", _pidController.atSetpoint());
        SmartDashboard.putBoolean("Shooter/Turret/LeftLimit", _leftLimit.get());
        SmartDashboard.putBoolean("Shooter/Turret/RightLimit", _rightLimit.get());
    }

    private static class TurretPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.02;
        private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.002;

        public TurretPidController() {
            super(P, I, D);
        }
    }
}
