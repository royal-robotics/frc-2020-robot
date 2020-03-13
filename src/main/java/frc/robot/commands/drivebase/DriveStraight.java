package frc.robot.commands.drivebase;

import java.util.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.trajectory.*;
import frc.robot.subsystems.drivebase.*;

public class DriveStraight extends DrivePath {
    private final DrivebaseSubsystem _drivebase;
    private final double _distance;

	public DriveStraight(DrivebaseSubsystem drivebase, double distance) {
        super(drivebase);
        _drivebase = drivebase;
        _distance = distance;
    }

	@Override
    protected Trajectory getTrajectory() {
        _drivebase.reset();
        return TrajectoryGenerator.generateTrajectory(
            new Pose2d(0, 0, new Rotation2d(0)),
            List.of(),
            new Pose2d(_distance, 0, new Rotation2d(0)),
            getTrajectoryConfig()
        );
    }

    private TrajectoryConfig getTrajectoryConfig() {
        final var MaxTrajectorySpeed = 1.0;
        final var MaxTrajectoryAcceleration = 0.5;
        return new TrajectoryConfig(MaxTrajectorySpeed, MaxTrajectoryAcceleration)
            .setKinematics(DriveKinematics)
            .addConstraint(DriveVoltageConstraint)
            .setReversed(_distance < 0.0);
    }

}
