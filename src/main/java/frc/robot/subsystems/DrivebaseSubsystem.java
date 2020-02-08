package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.*;

public final class DrivebaseSubsystem extends RoyalSubsystem
{
    private final MotorGroup _leftMotors;
    private final MotorGroup _rightMotors;

    private final DifferentialDriveOdometry _odometry;
    private final Gyro _gyro;

    public DrivebaseSubsystem()
    {
        _leftMotors = new MotorGroup(Components.Drivebase.left_motor1, Components.Drivebase.left_motor2);
        _rightMotors = new MotorGroup(Components.Drivebase.right_motor1, Components.Drivebase.right_motor2);

        _gyro = new ADXRS450_Gyro();
        _gyro.reset();
        _gyro.calibrate();

        _leftMotors.resetPosition();
        _rightMotors.resetPosition();

        _odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    @Override
    public void periodic() {
        final var gyroAngle = Rotation2d.fromDegrees(getHeading());
        final var leftDistance = _leftMotors.getPosition();
        final var rightDistance = _rightMotors.getPosition();
        _odometry.update(gyroAngle, leftDistance, rightDistance);
    }

    public void setPower(double leftPower, double rightPower) {
        _leftMotors.setSpeed(-leftPower);
        _rightMotors.setSpeed(rightPower);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        _leftMotors.setVoltage(leftVolts);
        _rightMotors.setVoltage(-rightVolts);
        // m_drive.feed();
    }

    public double getHeading() {
        final boolean isGyroReversed = false;
        return Math.IEEEremainder(_gyro.getAngle(), 360) * (isGyroReversed ? -1.0 : 1.0);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        final var leftRate = _leftMotors.getVelocity();
        final var rightRate = _rightMotors.getVelocity();
        return new DifferentialDriveWheelSpeeds(leftRate, rightRate);
    }

    public Pose2d getPose() {
        return _odometry.getPoseMeters();
    }
}
