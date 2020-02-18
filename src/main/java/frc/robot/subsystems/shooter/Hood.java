package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import frc.libs.components.RoyalPidController;
import frc.robot.*;
import frc.robot.subsystems.*;

public class Hood extends RoyalArcSubsystem {
    private final PWM _motor;

    public Hood() {
        super(Components.Shooter.hoodEncoder, new HoodPidController(), new SimpleMotorFeedforward(0.451, 0.102, 0.000757));
        _motor = Components.Shooter.hood;
        gearsRatio(256.0, 1.57, 54.98);
    }

    @Override
    public void stop() {
        _motor.setSpeed(0.0);
    }

    @Override
    public void setPower(double power) {
        _motor.setSpeed(power);
    }

    @Override
    protected void setMotorOutput(double power) {
        setPower(power);
    }

    @Override
    protected void updateDiagnostics() {
        // TODO Auto-generated method stub

    }

    private static class HoodPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.02;
        private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.002;

        public HoodPidController() {
            super(P, I, D);
        }
    }
}
