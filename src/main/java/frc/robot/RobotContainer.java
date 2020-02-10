package frc.robot;

import java.util.List;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer
{
    private static Joystick driver = new Joystick(0);
    // private static Joystick operator = new Joystick(1);

    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final ElevatorSubsystem elevator = new ElevatorSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();

    public RobotContainer()
    {
        BindCommands();

        drivebase.setDefaultCommand(new ManualDrive(drivebase, driver));
    }

    private void BindCommands()
    {
        // Example: When operator joystick presses A or B, do something
        // new JoystickButton(operator, 1).whenPressed(new ExtendElevator(elevator));
        // new JoystickButton(operator, 2).whenPressed(new RetractElevator(elevator));

        new JoystickButton(driver, 1).whenPressed(getAutonomousCommand());
    }

    public Command getAutonomousCommand() {
        // TODO: Run the robot drive charactisation to get these numbers.
        final var ksVolts = 0.22;
        final var kvVoltSecondsPerMeter = 1.98;
        final var kaVoltSecondsSquaredPerMeter = 0.2;
        final var motorFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

        final double kTrackwidthMeters = 0.69; // TODO: Measure the distance between the wheels.
        final var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

        final var exampleTrajectory = getTrajectory(kDriveKinematics, motorFeedforward);

        // TODO: Tune these values
        final var kPDriveVel = 8.5;
        final var kRamseteB = 2.0;
        final var kRamseteZeta = 0.7;
        final var ramseteCommand = new RamseteCommand(
            exampleTrajectory,
            drivebase::getPose,
            new RamseteController(kRamseteB, kRamseteZeta),
            motorFeedforward,
            kDriveKinematics,
            drivebase::getWheelSpeeds,
            new PIDController(kPDriveVel, 0, 0),
            new PIDController(kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            drivebase::tankDriveVolts,
            drivebase
        );

        // Run path following command, then stop at the end.
        return ramseteCommand.andThen(() -> drivebase.tankDriveVolts(0, 0));
    }

    // TODO: Get this from the pathweaver files.
    private final Trajectory getTrajectory(DifferentialDriveKinematics kDriveKinematics, SimpleMotorFeedforward motorFeedforward) {
        final var maxVoltage = 10.0; // TODO: Tune this?
        final var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(motorFeedforward, kDriveKinematics, maxVoltage);

        final var kMaxSpeedMetersPerSecond = 12.0;
        final var kMaxAccelerationMetersPerSecondSquared = 12.0;
        final var config = new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(kDriveKinematics)
            .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow.  All units in meters.
        return TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(
                // new Translation2d(1, 1),
                // new Translation2d(2, -1)
            ),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            // Pass config
            config
        );
    }
}
