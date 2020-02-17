package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Components;

public class Balancer {
    private final WPI_TalonSRX _balance;
    private final BalancerPidController _balancerPID;
    private final BuiltInAccelerometer _accelerometer;

    private boolean _isEnabled = false;

    public Balancer() {
        // To count as balanced we need to be within 8 degrees.
        final var BalanceTolerance = 4.0;
        _balance = Components.Climber.balance;
        _balancerPID = new BalancerPidController();
        _balancerPID.setTolerance(BalanceTolerance);

        _accelerometer = Components.Climber.accelerometer;
    }

    public void enable() {
        if (!_isEnabled) {
            _isEnabled = true;
            _balancerPID.reset();
        }
    }

    public void disable() {
        _isEnabled = false;
    }

    public void updateControlLoop() {
        _balance.set(_balancerPID.calculate(getXAngle()));
    }

    public void updateDiagnostics() {
        SmartDashboard.putNumber("Climber/Balancer/xAngle", getXAngle());
    }

    private double getXAngle() {
        return _accelerometer.getX() * 90.0;
    }

    private class BalancerPidController extends PIDController {
        private static final double LoopIntervalMs = 20.0;
        private static final double LoopInterval = LoopIntervalMs / 1000.0;

        // Output/Input Units: power per inch
        // TODO: Should these be multiplied by 12 and switched to voltage?
        private static final double P = 0.05;
        private static final double I = 0.01 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.01;
        public BalancerPidController() {
            super(P, I, D, LoopInterval);
        }
    }
}
