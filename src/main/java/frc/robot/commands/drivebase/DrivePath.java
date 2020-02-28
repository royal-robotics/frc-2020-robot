package frc.robot.commands.drivebase;

import java.util.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.drivebase.*;

public class DrivePath extends RamseteCommand {
    private final DrivebaseSubsystem _drivebase;

    private static final double StaticVolts = 0.187;
    private static final double VoltSecondsPerMeter = 2.55;
    private static final double VoltSecondsSquaredPerMeter = 0.543;
    private static final SimpleMotorFeedforward MotorFeedforward = new SimpleMotorFeedforward(StaticVolts, VoltSecondsPerMeter, VoltSecondsSquaredPerMeter);

    private static final double MetersPerInch =  0.0254;
    private static final double kTrackwidth = 25.0 * MetersPerInch; // TODO: Get a more accurate width number from SolidWorks.
    private static final DifferentialDriveKinematics DriveKinematics = new DifferentialDriveKinematics(kTrackwidth);

    private static final double MaxVoltage = 10.0;
    private static final  DifferentialDriveVoltageConstraint DriveVoltageConstraint = new DifferentialDriveVoltageConstraint(MotorFeedforward, DriveKinematics, MaxVoltage);

    private static final double RamseteB = 2.0;
    private static final double RamseteZeta = 0.7;

    private static final double P_DriveVelocity = 0.005;
    private static final double I_DriveVelocity = 0.0;
    private static final double D_DriveVelocity = 0.0;

    public DrivePath(DrivebaseSubsystem drivebase, boolean inverted) {
        super(
            getTrajectory(),
            () -> drivebase.getPose(inverted),
            new RamseteController(RamseteB, RamseteZeta),
            MotorFeedforward,
            DriveKinematics,
            () -> drivebase.getWheelSpeeds(inverted),
            new PIDController(P_DriveVelocity, I_DriveVelocity, D_DriveVelocity),
            new PIDController(P_DriveVelocity, I_DriveVelocity, D_DriveVelocity),
            (leftVolts, rightVolts) -> drivebase.setVolts(leftVolts, rightVolts, inverted),
            drivebase
        );

        _drivebase = drivebase;
    }

    @Override
    public void initialize() {
        super.initialize();
        _drivebase.setBreakMode(true);
    }

    private static final Trajectory getTrajectory() {
        final var kMaxSpeedMetersPerSecond = 1.0;
        final var kMaxAccelerationMetersPerSecondSquared = 0.5;
        final var config = new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(DriveKinematics)
            .addConstraint(DriveVoltageConstraint);

        // An example trajectory to follow.  All units in meters.
        return TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(
                // new Translation2d(5.0, 0.0)
                // new Translation2d(2, -1)
            ),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(1.0, 0, new Rotation2d(0)),
            // Pass config
            config
        );
    }
}
