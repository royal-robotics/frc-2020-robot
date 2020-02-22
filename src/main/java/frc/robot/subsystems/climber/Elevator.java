package frc.robot.subsystems.climber;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.libs.components.*;
import frc.robot.*;

public class Elevator extends PIDSubsystem {
    private final CANSparkMax _motor;
    private final EncoderGroup _encoder;

    public Elevator() {
        super(new ElevatorPidController(), 0.0);
        _motor = Components.Climber.elevator1;
        final var elevator2 = Components.Climber.elevator2;
        elevator2.follow(_motor);
        _motor.setInverted(true);
        elevator2.setInverted(true);

        // We assume that when the robot turns on, the elevator will be at the bottom.
        final var CompositeRatio1 = 13.0 / 60.0;
        final var CompositeRatio2 = 18.0 / 36.0;
        final var GearRatio = CompositeRatio1 * CompositeRatio2;
        final var OutputPitchDiameter = 1.880;
        final var OutputPulleyCircumference = Math.PI * OutputPitchDiameter;
        final var InchesPerTurn = OutputPulleyCircumference * GearRatio;
        _encoder = new EncoderGroup(InchesPerTurn, false, _motor.getEncoder(), elevator2.getEncoder());
    }

    public void setHeight(double height) {
        this.setSetpoint(height);
    }

    public double getHeight() {
        return getMeasurement();
    }

    public void setPower(double power) {
        // Disable the pid control if we're controlling it manually.
        if (isEnabled()) {
            disable();
        }

        _motor.set(clampOutputAtBounds(power));
    }

    public boolean isAtSetpoint() {
        final var Tolerance = 1.0;
        final var measurement = getMeasurement();
        return Math.abs(measurement - m_controller.getSetpoint()) < Tolerance;
    }

    @Override
    protected void useOutput(double pidError, double setpoint) {
        // Extra power to account for gravity + static friction
        final var feedforward = 0.0;

        final var power = feedforward + pidError;
        _motor.set(clampOutputAtBounds(power));
    }

    @Override
    protected double getMeasurement() {
        return _encoder.getPosition();
    }

    private double clampOutputAtBounds(double output) {
        // TODO: Clamp to the position limits
        return output;
    }

    @Override
    public void periodic() {
        super.periodic();
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Climber/Elevator/Power", _motor.get());
        SmartDashboard.putNumber("Climber/Elevator/Voltage", _motor.getAppliedOutput());
        SmartDashboard.putNumber("Climber/Elevator/Position", getMeasurement());
        SmartDashboard.putNumber("Climber/Elevator/Setpoint", m_controller.getSetpoint());
        SmartDashboard.putBoolean("Climber/Elevator/PidControlEnabled", isEnabled());
    }

    private static class ElevatorPidController extends PIDController {
        private static final double LoopIntervalMs = 20.0;
        private static final double LoopInterval = LoopIntervalMs / 1000.0;

        // Output/Input Units: power per inch
        private static final double P = 0.05;
        private static final double I = 0.000 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.01;

        public ElevatorPidController() {
            super(P, I, D, LoopInterval);
        }
    }
}
