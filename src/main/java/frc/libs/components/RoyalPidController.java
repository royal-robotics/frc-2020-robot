package frc.libs.components;

import edu.wpi.first.wpilibj.controller.*;

public class RoyalPidController extends PIDController {
    // We assume calculate will be called periodicly at this interval.
    // This is the interval of the wpilib periodic loop.
    protected static final double LoopIntervalMs = 20.0;
    protected static final double LoopInterval = LoopIntervalMs / 1000.0;

    private Double _lastMeasure = null;

    public RoyalPidController(double p, double i, double d) {
        super(p, i, d, LoopInterval);
        this.setSetpoint(0.0);
    }

    // Base the atSetpoint evaluation on the last measured value.
    // The default implementation based it on calculated errors.
    @Override
    public boolean atSetpoint() {
        if (_lastMeasure == null) {
            return false;
        }

        final var Tolerence = 3.0;
        return Math.abs(getSetpoint() - _lastMeasure) < Tolerence;
    }

    @Override
    public double calculate(double measure) {
        _lastMeasure = measure;
        return super.calculate(measure);
    }

    @Override
    public void reset() {
        super.reset();
        _lastMeasure = null;
    }
}
