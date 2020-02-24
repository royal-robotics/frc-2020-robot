package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Components;

public class Balancer extends PIDSubsystem {
    private final WPI_TalonSRX _motor;
    private final BuiltInAccelerometer _accelerometer;

    public Balancer() {
        super(new BalancerPidController(), 0.0);
        _motor = Components.Climber.balance;
        _accelerometer = Components.Climber.accelerometer;
    }

    public void setPower(double power) {
        // Disable the pid control if we're controlling it manually.
        if (isEnabled()) {
            disable();
        }

        _motor.set(power);
    }

    @Override
    protected void useOutput(double pidError, double setpoint) {
        // Extra power to account for static friction?
        final var feedforward = 0.0;
        final var power = feedforward + pidError;
        _motor.set(-power);
    }

    @Override
    public void disable() {
        super.disable();
        _motor.set(0.0);
    }

    @Override
    protected double getMeasurement() {
        return getXAngle();
    }

    public double getXAngle() {
        return _accelerometer.getX() * 90.0;
    }

    @Override
    public void periodic() {
        super.periodic();
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Climber/Balancer/Power", _motor.get());
        SmartDashboard.putNumber("Climber/Balancer/X-Angle", getXAngle());
    }

    private static class BalancerPidController extends PIDController {
        private static final double LoopIntervalMs = 20.0;
        private static final double LoopInterval = LoopIntervalMs / 1000.0;

        // Output/Input Units: power per degree
        private static final double P = 0.03;
        private static final double I = 0.00 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.000;
        public BalancerPidController() {
            super(P, I, D, LoopInterval);
        }
    }
}
