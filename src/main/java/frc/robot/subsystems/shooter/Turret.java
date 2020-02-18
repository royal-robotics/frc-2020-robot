package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.libs.components.RoyalPidController;
import frc.robot.*;
import frc.robot.subsystems.*;

public class Turret extends RoyalArcSubsystem {
    private final WPI_TalonSRX _motor;

    public Turret() {
        super(Components.Shooter.turretEncoder, new TurretPidController(), new SimpleMotorFeedforward(0.451, 0.102, 0.000757));
        _motor = Components.Shooter.turret;
        gearsRatio(256.0, 4.71, 40.84);
    }


    @Override
    public void stop() {
        _motor.set(0.0);
    }

    @Override
    public void setPower(double power) {
        _motor.set(power);

    }

    @Override
    protected void setMotorOutput(double power) {
        _motor.setVoltage(power);
    }

    @Override
    protected void updateDiagnostics() {
        SmartDashboard.putNumber("Shooter/Turret/Power", _motor.get());
        SmartDashboard.putNumber("Shooter/Turret/Voltage", _motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Shooter/Turret/Degrees", getAngle());
        SmartDashboard.putNumber("Shooter/Turret/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Shooter/Turret/OnTarget", _pidController.atSetpoint());
    }

    private static class TurretPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.02;
        private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.002;

        public TurretPidController() {
            super(P, I, D);
        }
    }
}
