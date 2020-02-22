package frc.robot.subsystems.drivebase;

import frc.robot.Components;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.*;
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

        _leftGearbox = new DriveGearbox(true, Components.Drivebase.leftMotor1, Components.Drivebase.leftMotor2);
        _rightGearbox = new DriveGearbox(false, Components.Drivebase.rightMotor1, Components.Drivebase.rightMotor2);

        _gyro = Components.Drivebase.gyro;

        _odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    @Override
    public void periodic() {
        final var gyroAngle = Rotation2d.fromDegrees(getHeading());
        final var leftDistance = _leftGearbox.getPosition();
        final var rightDistance = _rightGearbox.getPosition();
        _odometry.update(gyroAngle, leftDistance, rightDistance);

        final var wheelSpeeds = getWheelSpeeds();
        SmartDashboard.putNumber("drive/left/velocity", getWheelSpeeds().leftMetersPerSecond);
        SmartDashboard.putNumber("drive/right/velocity", getWheelSpeeds().rightMetersPerSecond);
    }

    public void setPower(double leftPower, double rightPower) {
        _leftGearbox.setPower(leftPower);
        _rightGearbox.setPower(rightPower);
    }

    public void setBreakMode(boolean breakModeOn) {
        _leftGearbox.setBreakMode(breakModeOn);
        _rightGearbox.setBreakMode(breakModeOn);
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
        final var leftVelocity = _leftGearbox.getVelocity();
        final var rightVelocity = _rightGearbox.getVelocity();
        return new DifferentialDriveWheelSpeeds(leftVelocity, rightVelocity);
    }

    public Pose2d getPose() {
        return _odometry.getPoseMeters();
    }
}
