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

    private static final String positionSettingName = "turret-position-v8";

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
        // final var feedforwardHelper = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);
        // final var feedforwardVelocity = getMeasurement() < setpoint ? 30.0 : -30.0;
        // final var feedforward = feedforwardHelper.calculate(feedforwardVelocity);
        final var feedforward = 0.0;

        var voltage = clampOnConstraints(feedforward + pidError);
        updateSave(voltage);

        if (!this.isAtSetpoint()) {
            _motor.setVoltage(voltage);
        }
    }

    @Override
    protected double clampOnConstraints(double value) {
        value = super.clampOnConstraints(value);
        if (_leftLimit.get() && value > 0.0) {
            value = 0.0;
        } else if (_rightLimit.get() && value < 0.0) {
            value = 0.0;
        }

        return value;
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

        // If one of the limit switches is hit reset the position of the turret
        if (_leftLimit.get()) {
            updatePosition(90.0);
        } else if (_rightLimit.get()) {
            updatePosition(-90.0);
        }
    }


    private void updatePosition(double position) {
        final var newInitialPosition = position - getRelativeMeasurement();
        if (Math.abs(_initialPosition - newInitialPosition) > 0.1) {
            _initialPosition = newInitialPosition;
            save();
        }
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Shooter/Turret/Power", _motor.get());
        SmartDashboard.putNumber("Shooter/Turret/Voltage", _motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Shooter/Turret/Degrees", getMeasurement());
        SmartDashboard.putNumber("Shooter/Turret/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Shooter/Turret/OnTarget", this.isAtSetpoint());
        SmartDashboard.putBoolean("Shooter/Turret/LeftLimit", _leftLimit.get());
        SmartDashboard.putBoolean("Shooter/Turret/RightLimit", _rightLimit.get());
    }

    private static class TurretPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.04 * 12.0;
        private static final double I = 0.05 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.03;

        public TurretPidController() {
            super(P, I, D);
        }
    }
}
