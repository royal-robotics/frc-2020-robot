package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.*;

public class Platform {
    private final WPI_TalonSRX _platform;
    private final Encoder _encoder;
    private final PlatformPidController _platformPID;
    private final SimpleMotorFeedforward _feedforward;

    private final double PulsesPerRotation = 256.0;
    private final double PlatformCircumference = 40.84;
    private final double IdlerCircumference = 4.71;
    private final double DistancePerDegree = PlatformCircumference/360;

    private boolean _underPidControl = false;

    public Platform() {
        _platform = Components.Turret.platform;
        _encoder = Components.Turret.platformEncoder;
        _platformPID = new PlatformPidController();
        _feedforward = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);

        _encoder.setDistancePerPulse(IdlerCircumference / PulsesPerRotation);
    }

    public void stop() {
        _underPidControl = false;
        _platform.set(0.0);
    }

    public void setPower(double power) {
        _underPidControl = false;
        _platform.set(ControlMode.PercentOutput, power);
    }

    public void setTargetAngle(double targetAngle) {
        _underPidControl = true;
        _platformPID.setSetpoint(targetAngle);
    }

    public boolean atTargetAngle() {
        return _platformPID.atSetpoint();
    }

    public double getAngle() {
        return _encoder.getDistance() / DistancePerDegree;
    }

    public void reset() {
        _platformPID.reset();
    }

    public void updateControlLoop() {
        if (_underPidControl) {
            System.out.println("underPidControl");
            final var feed = _feedforward.calculate(getAngle() > _platformPID.getSetpoint() ? -30.0 : 30.0);

            final var pidError = _platformPID.calculate(getAngle());
            _platform.setVoltage(pidError + feed);
        }
    }

    public void updateDiagnostics() {
        SmartDashboard.putNumber("Turret/Platform/Power", _platform.get());
        SmartDashboard.putNumber("Turret/Platform/Voltage", _platform.getMotorOutputVoltage());
        SmartDashboard.putNumber("Turret/Platform/Degrees", getAngle());
        SmartDashboard.putNumber("Turret/Platform/Setpoint", _platformPID.getSetpoint());
        SmartDashboard.putBoolean("Turret/Platform/OnTarget", _platformPID.atSetpoint());
    }
}
