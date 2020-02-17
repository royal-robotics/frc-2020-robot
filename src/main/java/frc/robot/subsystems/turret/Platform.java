package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Components;

//13
//1.5
public class Platform {
    private final WPI_TalonSRX _platform;
    private final Encoder _encoder;
    private final PlatformPidController _platformPID;

    private final double PulsesPerRotation = 256.0;
    private final double PlatformCircumference = 40.84;
    private final double IdlerCircumference = 4.71;
    private final double DistancePerDegree = PlatformCircumference/360;

    public Platform() {
        _platform = Components.Turret.platform;
        _encoder = Components.Turret.platformEncoder;
        _platformPID = new PlatformPidController();

        _encoder.setDistancePerPulse(IdlerCircumference/PulsesPerRotation);
    }

    public void stop() {
        _platform.set(0.0);
    }

    public void setPower(double power) {
        _platform.set(ControlMode.PercentOutput, power);
    }

    public void setGoalAngle(double targetAngle) {
        _platformPID.setSetpoint(targetAngle);
    }

    public boolean atGoalAngle() {
        return _platformPID.atSetpoint();
    }

    public double getAngle() {
        return _encoder.getDistance()/DistancePerDegree;
    }

    public void updateControlLoop() {
        _platform.set(_platformPID.calculate(getAngle()));
    }

    public void updateDiagnostics() {
        SmartDashboard.putNumber("Turret/Platform/Power", _platform.get());
        SmartDashboard.putNumber("Turret/Platform/Degrees", getAngle());
        SmartDashboard.putNumber("Turret/Platform/Setpoint", _platformPID.getSetpoint());
        SmartDashboard.putBoolean("Turret/Platform/OnTarget", _platformPID.atSetpoint());
    }
}
