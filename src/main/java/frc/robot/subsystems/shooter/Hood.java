package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.libs.components.*;
import frc.libs.subsystems.*;
import frc.libs.utils.*;
import frc.robot.*;

public class Hood extends PositionConstrainedSubsystem {
    private final PWM _motor;
    private final Encoder _encoder;

    private Double _lastStoredMeasure = null;

    private static final String positionSettingName = "hood-position-v5";

    public Hood() {
        super(new HoodPidController(), Settings.loadDouble(positionSettingName, 42.5), 43.0, 69.50, 0.5);
        _motor = Components.Shooter.hood;
        _encoder = Components.Shooter.hoodEncoder;

        final var PulsesPerRotation = 256.0;
        final var FinalGearRatio  = 1.57;
        final var TurretCircumference = 54.98;
        final var DistancePerPulse = FinalGearRatio / PulsesPerRotation;
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
        _motor.setSpeed(power);
    }

    @Override
    protected void setOutput(double pidError, double setpoint) {
        // final var feedforwardHelper = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);
        // final var feedforwardVelocity = getMeasurement() < setpoint ? 30.0 : -30.0;
        // final var feedforward = feedforwardHelper.calculate(feedforwardVelocity);
        final var feedforward = 0.0;

        var power = clampOnConstraints(feedforward + pidError);
        updateSave(power);
        _motor.setSpeed(power);
    }

    @Override
    protected double clampOnConstraints(double value) {
        // Something is a little wrong with the cordinantes of the BaseClass+Turret :(
        // And this was the easiest fix :(
        // return -super.clampOnConstraints(-value);
        return -super.clampOnConstraints(value);
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
        SmartDashboard.putNumber("Shooter/Hood/Power", _motor.getSpeed());
        SmartDashboard.putNumber("Shooter/Hood/Degrees", getMeasurement());
        SmartDashboard.putNumber("Shooter/Hood/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Shooter/Hood/OnTarget", isAtSetpoint());
    }

    private static class HoodPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.3;
        private static final double I = 0.03 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.03;

        public HoodPidController() {
            super(P, I, D);
        }
    }
}
