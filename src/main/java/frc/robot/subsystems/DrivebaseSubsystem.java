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
    private final DriveGearbox2019 _leftGearbox;
    private final DriveGearbox2019 _rightGearbox;

    private final DifferentialDriveOdometry _odometry;
    private final Gyro _gyro;

    public DrivebaseSubsystem()
    {
        Components.Drivebase.leftDrive1.setNeutralMode(NeutralMode.Brake);
        Components.Drivebase.leftDrive2.setNeutralMode(NeutralMode.Brake);
        Components.Drivebase.leftDrive3.setNeutralMode(NeutralMode.Brake);
        Components.Drivebase.rightDrive1.setNeutralMode(NeutralMode.Brake);
        Components.Drivebase.rightDrive2.setNeutralMode(NeutralMode.Brake);
        Components.Drivebase.rightDrive3.setNeutralMode(NeutralMode.Brake);

        _leftGearbox = new DriveGearbox2019(Components.Drivebase.leftDrive1, Components.Drivebase.leftEncoder, Components.Drivebase.leftDrive2, Components.Drivebase.leftDrive3);
        _rightGearbox = new DriveGearbox2019(Components.Drivebase.rightDrive1, Components.Drivebase.rightEncoder, Components.Drivebase.rightDrive2, Components.Drivebase.rightDrive3);

        _gyro = new ADXRS450_Gyro();
        _gyro.reset();
        _gyro.calibrate();

        _leftGearbox.resetPosition(getDistancePerPulse(), true);
        _rightGearbox.resetPosition(getDistancePerPulse(), false);

        _odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    private double getDistancePerPulse() {
        final var EncoderWheelGearRatio = 1.0;
        final var WheelDiameter = 0.152;//6.0;
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
        _odometry.update(gyroAngle, leftDistance, rightDistance);

        SmartDashboard.putNumber("Drive-Distance-Left", _leftGearbox.getPosition());
        SmartDashboard.putNumber("Drive-Distance-Right", _rightGearbox.getPosition());

        SmartDashboard.putNumber("Drive-Odometry-X", _odometry.getPoseMeters().getTranslation().getX());
        SmartDashboard.putNumber("Drive-Odometry-Y", _odometry.getPoseMeters().getTranslation().getY());
        SmartDashboard.putNumber("Drive-Odometry-Degrees", _odometry.getPoseMeters().getRotation().getDegrees());
    }

    public void setPower(double leftPower, double rightPower) {
        _leftGearbox.setPower(-leftPower);
        _rightGearbox.setPower(rightPower);
        // _leftGearbox.setVoltage(-10);
        // _rightGearbox.setVoltage(10);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        // _leftGearbox.setVoltage(-leftVolts);
        // _rightGearbox.setVoltage(rightVolts);
        // TODO: I think RamsetCommand assumes left and right are swapped
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
