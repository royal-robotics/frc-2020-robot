package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class DrivebaseSubsystem extends RoyalSubsystem
{
    private final DriveGearbox _leftGearbox;
    private final DriveGearbox _rightGearbox;
    private final Gyro _gyro;

    private final DifferentialDriveOdometry _odometry;

    public DrivebaseSubsystem() {
        // TODO: Set motors to coast (via the gearbox) when entering manual commands, set to break when entering auto ones
        // Components.Drivebase.leftMotor1.setNeutralMode(NeutralMode.Brake);
        // Components.Drivebase.leftMotor2.setNeutralMode(NeutralMode.Brake);
        // Components.Drivebase.rightMotor1.setNeutralMode(NeutralMode.Brake);
        // Components.Drivebase.rightMotor2.setNeutralMode(NeutralMode.Brake);

        _leftGearbox = new DriveGearbox(Components.Drivebase.leftMotor1, Components.Drivebase.leftMotor2);
        _rightGearbox = new DriveGearbox(Components.Drivebase.rightMotor1, Components.Drivebase.rightMotor2);

        _gyro = new ADXRS450_Gyro();
        _gyro.reset();
        _gyro.calibrate();

        _leftGearbox.resetPosition(getDistancePerPulse(), true);
        _rightGearbox.resetPosition(getDistancePerPulse(), false);

        _odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    private double getDistancePerPulse() {
        // TODO: Figure out what these values should be, use inches.
        final var EncoderWheelGearRatio = 1.0;
        final var WheelDiameter = 0.152;
        final var PulsesPerRotation = 256.0;
        final var InchesPerPulse = EncoderWheelGearRatio * WheelDiameter * Math.PI / PulsesPerRotation;

        final var pnumaticalWheelKludge = 0.9700;
        return InchesPerPulse * pnumaticalWheelKludge;
    }

    @Override
    public void periodic() {
        final var gyroAngle = Rotation2d.fromDegrees(getHeading());
        final var leftDistance = _leftGearbox.getPosition();
        final var rightDistance = _rightGearbox.getPosition();
        _odometry.update(gyroAngle, leftDistance, rightDistance); // TODO: Does this take degrees?

        // TODO: Make sure these values are in shuffleboard, along with all subsystem diagnostics.
        // SmartDashboard.putNumber("Drive-Distance-Left", _leftGearbox.getPosition());
        // SmartDashboard.putNumber("Drive-Distance-Right", _rightGearbox.getPosition());

        // SmartDashboard.putNumber("Drive-Odometry-X", _odometry.getPoseMeters().getTranslation().getX());
        // SmartDashboard.putNumber("Drive-Odometry-Y", _odometry.getPoseMeters().getTranslation().getY());
        // SmartDashboard.putNumber("Drive-Odometry-Degrees", _odometry.getPoseMeters().getRotation().getDegrees());
    }

    public void setPower(double leftPower, double rightPower) {
        _leftGearbox.setPower(-leftPower);
        _rightGearbox.setPower(rightPower);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        // TODO: I think RamsetCommand assumes left and right are swapped? Or somethings weird.
        // _leftGearbox.setVoltage(-leftVolts);
        // _rightGearbox.setVoltage(rightVolts);
        _leftGearbox.setVoltage(leftVolts);
        _rightGearbox.setVoltage(-rightVolts);
    }

    public double getHeading() {
        final boolean isGyroReversed = false;
        return Math.IEEEremainder(_gyro.getAngle(), 360) * (isGyroReversed ? -1.0 : 1.0);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        final var leftRate = _leftGearbox.getWheelSpeed();
        final var rightRate = _rightGearbox.getWheelSpeed();
        return new DifferentialDriveWheelSpeeds(leftRate, rightRate);
    }

    public Pose2d getPose() {
        return _odometry.getPoseMeters();
    }
}
