package frc.robot.subsystems.turret;

import frc.libs.components.RoyalPidController;

public class PlatformPidController extends RoyalPidController {
    // Output/Input Units: volts per degree
    private static final double P = 0.02;
    private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
    private static final double D = 0.002;

    public PlatformPidController() {
        super(P, I, D);
    }
}
