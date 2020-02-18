package frc.libs.components;

import edu.wpi.first.wpilibj.controller.*;

public class RoyalPidController extends PIDController {
    // We assume calculate will be called periodicly at this interval.
    // This is the interval of the wpilib periodic loop.
    protected static final double LoopIntervalMs = 20.0;
    protected static final double LoopInterval = LoopIntervalMs / 1000.0;

    public RoyalPidController(double p, double i, double d) {
        super(p, i, d, LoopInterval);
        this.setSetpoint(0.0);
    }
}
