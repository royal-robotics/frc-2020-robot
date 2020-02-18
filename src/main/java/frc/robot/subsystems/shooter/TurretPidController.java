package frc.robot.subsystems.shooter;

import frc.libs.components.RoyalPidController;

public class TurretPidController extends RoyalPidController {
    // Output/Input Units: volts per degree
    private static final double P = 0.02;
    private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
    private static final double D = 0.002;

    public TurretPidController() {
        super(P, I, D);
    }
}
