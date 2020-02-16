package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.controller.PIDController;

public class ElevatorPidController extends PIDController {
    private static final double LoopIntervalMs = 20.0;
    private static final double LoopInterval = LoopIntervalMs / 1000.0;

    // Output/Input Units: power per inch
    // TODO: Should these be multiplied by 12 and switched to voltage?
    private static final double P = 0.28;
    private static final double I = 0.1 / (1000.0 / LoopIntervalMs);
    private static final double D = 0.05;

    public ElevatorPidController() {
        super(P, I, D, LoopInterval);

        // this.setSetpoint(setpoint);
    }

    // TODO: Add a Notifier which runs the calculate method a regular interval

}
