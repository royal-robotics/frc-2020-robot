package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.PIDController;;
import edu.wpi.first.wpilibj.interfaces.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Components;

public class Balancer {
    private final WPI_TalonSRX _balance;
    private final BalancerPidController _balancerPID;
    private final BuiltInAccelerometer _accelerometer;

    public Balancer() {
        _balance = Components.Climber.balance;
        _balancerPID = new BalancerPidController();
        _balancerPID.setTolerance(1.0);

        _accelerometer = Components.Climber.accelerometer;
    }

    public void updateControlLoop() {
        _balance.set(_balancerPID.calculate(_accelerometer.getX()));
    }

    public void updateDiagnostics() {
        final var xAngle = _accelerometer.getX() * 90.0;
        SmartDashboard.putNumber("Climber/Balancer/xAngle", xAngle);
    }

    private class BalancerPidController extends PIDController {
        private static final double LoopIntervalMs = 20.0;
        private static final double LoopInterval = LoopIntervalMs / 1000.0;

        // Output/Input Units: power per inch
        // TODO: Should these be multiplied by 12 and switched to voltage?
        private static final double P = 0.28;
        private static final double I = 0.1 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.05;
        public BalancerPidController() {
            super(P, I, D, LoopInterval);
        }
    }
}
