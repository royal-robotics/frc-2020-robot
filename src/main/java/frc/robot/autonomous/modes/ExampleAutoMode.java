package frc.robot.autonomous.modes;

import frc.robot.autonomous.*;

public class ExampleAutoMode extends AutoModeBase {
    public ExampleAutoMode() {

    }

    // public Command getAutonomousCommand() {
    //     // TODO: Run frc-frc-characterization to get these values
    //     // final var ksVolts = 0.22;
    //     // final var kvVoltSecondsPerMeter = 1.98;
    //     // final var kaVoltSecondsSquaredPerMeter = 0.2;
    //     // final var motorFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

    //     // final double kTrackwidthMeters = 0.69; // TODO: Measure the distance between the wheels.
    //     // final var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    //     // final var exampleTrajectory = getTrajectory(kDriveKinematics, motorFeedforward);

    //     // TODO: Run frc-frc-characterization to get these values
    //     // final var kPDriveVel = 8.5;
    //     // final var kRamseteB = 2.0; // I think this can be fixed.
    //     // final var kRamseteZeta = 0.7; // I think this can be fixed.
    //     // final var ramseteCommand = new RamseteCommand(
    //     //     exampleTrajectory,
    //     //     drivebase::getPose,
    //     //     new RamseteController(kRamseteB, kRamseteZeta),
    //     //     motorFeedforward,
    //     //     kDriveKinematics,
    //     //     drivebase::getWheelSpeeds,
    //     //     new PIDController(kPDriveVel, 0, 0),
    //     //     new PIDController(kPDriveVel, 0, 0),
    //     //     // RamseteCommand passes volts to the callback, TODO: are the params swaped?
    //     //     drivebase::tankDriveVolts,
    //     //     drivebase
    //     // );

    //     // // Run path following command, then stop at the end.
    //     // return ramseteCommand.andThen(() -> drivebase.tankDriveVolts(0, 0));
    //     return null;
    // }

    // TODO: Get this from the pathweaver files.
    // private final Trajectory getTrajectory(DifferentialDriveKinematics kDriveKinematics, SimpleMotorFeedforward motorFeedforward) {
    //     final var maxVoltage = 10.0; // TODO: Tune this?
    //     final var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(motorFeedforward, kDriveKinematics, maxVoltage);

    //     final var kMaxSpeedMetersPerSecond = 12.0;
    //     final var kMaxAccelerationMetersPerSecondSquared = 12.0;
    //     final var config = new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
    //         .setKinematics(kDriveKinematics)
    //         .addConstraint(autoVoltageConstraint);

    //     // An example trajectory to follow.  All units in meters.
    //     return TrajectoryGenerator.generateTrajectory(
    //         // Start at the origin facing the +X direction
    //         new Pose2d(0, 0, new Rotation2d(0)),
    //         // Pass through these two interior waypoints, making an 's' curve path
    //         List.of(
    //             // new Translation2d(1, 1),
    //             // new Translation2d(2, -1)
    //         ),
    //         // End 3 meters straight ahead of where we started, facing forward
    //         new Pose2d(3, 0, new Rotation2d(0)),
    //         // Pass config
    //         config
    //     );
    // }
}
