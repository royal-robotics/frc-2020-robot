package frc.robot.subsystems.drivebase;

import frc.robot.Components;
import frc.robot.subsystems.*;
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
        _leftGearbox = new DriveGearbox(false, Components.Drivebase.leftMotor1, Components.Drivebase.leftMotor2);
        _rightGearbox = new DriveGearbox(true, Components.Drivebase.rightMotor1, Components.Drivebase.rightMotor2);
        _gyro = Components.Drivebase.gyro;
        _odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    @Override
    public void periodic() {
        final var gyroAngle = Rotation2d.fromDegrees(getHeading());
        final var leftDistance = _leftGearbox.getPosition();
        final var rightDistance = _rightGearbox.getPosition();
        _odometry.update(gyroAngle, leftDistance, rightDistance);

        final var wheelSpeeds = getWheelSpeeds(false);
        SmartDashboard.putNumber("drive/heading/degrees", getHeading());
        SmartDashboard.putNumber("drive/velocity/left", wheelSpeeds.leftMetersPerSecond);
        SmartDashboard.putNumber("drive/velocity/right", wheelSpeeds.rightMetersPerSecond);
        SmartDashboard.putNumber("drive/position/left", _leftGearbox.getPosition());
        SmartDashboard.putNumber("drive/position/right", _rightGearbox.getPosition());
        SmartDashboard.putNumber("drive/odometry/x", _odometry.getPoseMeters().getTranslation().getX());
        SmartDashboard.putNumber("drive/odometry/y", _odometry.getPoseMeters().getTranslation().getY());
        SmartDashboard.putNumber("drive/odometry/angle", _odometry.getPoseMeters().getRotation().getDegrees());
    }

    public void setPower(double leftPower, double rightPower) {
        _leftGearbox.setPower(leftPower);
        _rightGearbox.setPower(rightPower);
    }

    public void setBreakMode(boolean breakModeOn) {
        _leftGearbox.setBreakMode(breakModeOn);
        _rightGearbox.setBreakMode(breakModeOn);
    }

    public void setVolts(double leftVolts, double rightVolts, boolean inverted) {
        _leftGearbox.setVoltage(inverted ? -leftVolts : leftVolts);
        _rightGearbox.setVoltage(inverted ? -rightVolts : rightVolts);
    }

    public void reset() {
        _leftGearbox.reset();
        _rightGearbox.reset();
        _odometry.resetPosition(new Pose2d(0, 0, Rotation2d.fromDegrees(0)), Rotation2d.fromDegrees(getHeading()));
    }

    public double getHeading() {
        final boolean isGyroReversed = true;
        return Math.IEEEremainder(_gyro.getAngle(), 360.0) * (isGyroReversed ? -1.0 : 1.0);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds(boolean inverted) {
        final var leftVelocity = inverted ? -_leftGearbox.getVelocity() : _leftGearbox.getVelocity();
        final var rightVelocity = inverted ? -_rightGearbox.getVelocity() : _rightGearbox.getVelocity();
        return new DifferentialDriveWheelSpeeds(leftVelocity, rightVelocity);
    }

    public Pose2d getPose(boolean inverted) {
        if (inverted) {
            return new Pose2d(
                _odometry.getPoseMeters().getTranslation().rotateBy(Rotation2d.fromDegrees(180.0)),
                _odometry.getPoseMeters().getRotation());
        } else {
            return _odometry.getPoseMeters();
        }
    }
}
