package frc.robot.subsystems.shooter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.Scanner;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.libs.components.RoyalPidController;
import frc.robot.*;
import frc.robot.subsystems.RoyalSubsystem;

public class Turret extends RoyalSubsystem {
    private final WPI_TalonSRX _motor;
    private final Encoder _encoder;
    private final double _encoderStartOffset;
    private final RoyalPidController _pidController;
    private final SimpleMotorFeedforward _feedforward;

    private boolean _underSetpointControl = false;

    public Turret() {
        _motor = Components.Shooter.turret;
        _encoder = Components.Shooter.turretEncoder;
        _encoderStartOffset = loadPosition();

        _pidController = new TurretPidController();
        _feedforward = new SimpleMotorFeedforward(0.451, 0.102, 0.000757);

        final var PulsesPerRotation = 256.0;
        final var PlatformCircumference = 40.84;
        final var IdlerCircumference = 4.71;
        final var DistancePerDegree = PlatformCircumference / 360.0;
        final var DistancePerPulse = IdlerCircumference / PulsesPerRotation;
        final var DegreesPerPulse = DistancePerPulse / DistancePerDegree;
        _encoder.setDistancePerPulse(DegreesPerPulse);

    }

    public void stop() {
        _underSetpointControl = false;
        _motor.set(0.0);
    }

    public void setPower(double power) {
        _underSetpointControl = false;
        _motor.set(ControlMode.PercentOutput, power);
    }

    public void setTargetAngle(double targetAngle) {
        _underSetpointControl = true;
        _pidController.setSetpoint(targetAngle);
    }

    public boolean atTargetAngle() {
        return _pidController.atSetpoint();
    }

    public double getAngle() {
        return _encoder.getDistance() + _encoderStartOffset;
    }

    public void reset() {
        _pidController.reset();
    }

    public void savePosition() {
        final var settingsDirectoryName = Paths.get(Filesystem.getOperatingDirectory().getAbsolutePath(), "settings");
        final var settingsDirectory = settingsDirectoryName.toFile();
        if (!settingsDirectory.exists()) {
            settingsDirectory.mkdirs();
        }

        final var settingsFileName = Paths.get(settingsDirectory.getAbsolutePath(), "turret-position-v3.txt");
        final var settingsFile = settingsFileName.toFile();
        try {
            final var fstream = new FileWriter(settingsFile, false);
            try (final var out = new BufferedWriter(fstream)) {
                final var position = getAngle();
                final var bigDecimal = BigDecimal.valueOf(position).setScale(4, RoundingMode.HALF_UP);
                out.write(bigDecimal.toPlainString());
            }
        } catch (IOException e) {
            System.out.println("Warning: Failed to save turret position");
        }
    }

    private double loadPosition() {
        final var settingsDirectoryName = Paths.get(Filesystem.getOperatingDirectory().getAbsolutePath(), "settings");
        final var settingsDirectory = settingsDirectoryName.toFile();
        if (!settingsDirectory.exists()) {
            settingsDirectory.mkdirs();
        }

        final var settingsFileName = Paths.get(settingsDirectory.getAbsolutePath(), "turret-position-v3.txt");
        final var settingsFile = settingsFileName.toFile();
        try {
            try(final var settingsReader = new Scanner(settingsFile)) {
                if (!settingsReader.hasNextDouble()) {
                    System.out.println("Warning: Corrupt settings file");
                    return 0.0;
                }

                final var position = settingsReader.nextDouble();
                System.out.println("Turrent Loaded Position: " + position);
                return position;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: No turrent position to load");
            return 0.0;
        }
    }

    @Override
    public void periodic() {
        if (_underSetpointControl) {
            final var feedforwardVelocity = 30.0;
            final var positiveFeedforward = getAngle() < _pidController.getSetpoint();
            final var feedforward = _feedforward.calculate(positiveFeedforward ? feedforwardVelocity : -feedforwardVelocity);

            final var pidError = _pidController.calculate(getAngle());
            _motor.setVoltage(pidError + feedforward);
        }

        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Turret/Platform/Power", _motor.get());
        SmartDashboard.putNumber("Turret/Platform/Voltage", _motor.getMotorOutputVoltage());
        SmartDashboard.putNumber("Turret/Platform/Degrees", getAngle());
        SmartDashboard.putNumber("Turret/Platform/Setpoint", _pidController.getSetpoint());
        SmartDashboard.putBoolean("Turret/Platform/OnTarget", _pidController.atSetpoint());
    }

    private class TurretPidController extends RoyalPidController {
        // Output/Input Units: volts per degree
        private static final double P = 0.02;
        private static final double I = 0.001 / (1000.0 / LoopIntervalMs);
        private static final double D = 0.002;

        public TurretPidController() {
            super(P, I, D);
        }
    }
}
