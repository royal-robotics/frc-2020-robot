package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.controller.PIDController;

public class PlatformPidController extends PIDController{
    private static final double LoopIntervalMs = 20.0;
    private static final double LoopInterval = LoopIntervalMs / 1000.0;

    // Output/Input Units: power per inch
    // TODO: Should these be multiplied by 12 and switched to voltage?
    private static final double P = 0.02;
    private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
    private static final double D = 0.002;

    public PlatformPidController() {
        super (P, I, D, LoopInterval);
        this.setSetpoint(0.0);
        this.setTolerance(3.0);
    }

    // TODO: Add a Notifier which runs the calculate method a regular interval
}
