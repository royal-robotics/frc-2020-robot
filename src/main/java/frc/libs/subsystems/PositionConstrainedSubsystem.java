package frc.libs.subsystems;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.components.*;

public abstract class PositionConstrainedSubsystem extends PIDSubsystem {
    protected final RoyalPidController _pidController;
    protected double _initialPosition;
    protected final double _minPosition;
    protected final double _maxPosition;
    protected final double _tolerance;

    public PositionConstrainedSubsystem(RoyalPidController pidController, double initial, double min, double max, double tolerance) {
        super(pidController, initial);
        _pidController = pidController;
        _initialPosition = initial;
        _minPosition = min;
        _maxPosition = max;
        _tolerance = tolerance;
    }

    @Override
    protected final double getMeasurement() {
        return getRelativeMeasurement() + _initialPosition;
    }

    protected abstract double getRelativeMeasurement();

    public boolean isAtSetpoint() {
        final var measurement = getMeasurement();
        return Math.abs(measurement - _pidController.getSetpoint()) < _tolerance;
    }

    public boolean isWithinConstraints() {
        final var measurement = getMeasurement();
        return measurement < _maxPosition && measurement > _minPosition;
    }

    public boolean underSetpointControl() {
        return isEnabled() && !isWithinConstraints() && !isAtSetpoint();
    }

    @Override
    protected void useOutput(double pidError, double setpoint) {
        if (!this.isEnabled()) {
            // Don't set the output if the subsystem isn't enabled.
            return;
        }

        if (isAtSetpoint()) {
            // Don't set the output if the subsystem is at the target.
            return;
        }

        setOutput(pidError, setpoint);
    }

    protected double clampOnConstraints(double value) {
        final var position = getMeasurement();
        if (position > _maxPosition && value > 0.0) {
            value = 0.0;
        } else if (position < _minPosition && value < 0.0) {
            value = 0.0;
        }

        return value;
    }

    protected abstract void setOutput(double pidError, double setpoint);
}
