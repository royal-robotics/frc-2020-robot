package frc.robot.subsystems;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.Scanner;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import frc.libs.components.RoyalPidController;
import frc.robot.subsystems.RoyalSubsystem;

public abstract class RoyalArcSubsystem extends RoyalSubsystem {
    protected double DistancePerDegree = 0;
    protected double DistancePerPulse = 0;
    protected double DegreesPerPulse = 0;
    protected double maxAngle = Double.POSITIVE_INFINITY;
    protected double minAngle = Double.NEGATIVE_INFINITY;

    // Declare motor in subclass
    protected final Encoder _encoder;
    protected final double _encoderStartOffset;
    protected final RoyalPidController _pidController;
    protected final SimpleMotorFeedforward _feedforward;

    protected boolean _underSetpointControl = false;

    public RoyalArcSubsystem(Encoder encoder, RoyalPidController pidController, SimpleMotorFeedforward feedforward) {
        _encoder = encoder;
        _encoderStartOffset = loadPosition();
        _pidController = pidController;
        _feedforward = feedforward;
    }

    // Calculates the degrees of the gear we want to measure
    // based on the encoder rotations
    public void gearsRatio(double pulsesPerRotation, double encoderGearCircumference, double measuringGearCircumference) {
        if (pulsesPerRotation == 0 || encoderGearCircumference == 0 || measuringGearCircumference == 0) {
            System.out.println("Gear ratio requires non-zero values");
        }
        DistancePerPulse = encoderGearCircumference / pulsesPerRotation;
        DistancePerDegree = measuringGearCircumference/360;
        DegreesPerPulse = DistancePerPulse / DistancePerDegree;

        _encoder.setDistancePerPulse(DegreesPerPulse); // Counts the angle of the gear we want
    }

    public abstract void stop();

    public abstract void setPower(double power);

    protected abstract void updateDiagnostics();

    protected abstract void setMotorOutput(double power);

    // Set maximum angle
    public void setMaxAngle(double angle) {
        maxAngle = angle;
    }

    // Set minimum angle
    public void setMinAngle(double angle) {
        minAngle = angle;
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
            setMotorOutput(pidError + feedforward);
            //_motor.setVoltage(pidError + feedforward);
        }

        updateDiagnostics();
    }

}
