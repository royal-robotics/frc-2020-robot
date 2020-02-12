package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.libs.controls.ControlsFactory;
import frc.libs.controls.Controllers.Controller;
import frc.libs.controls.Controllers.Logitech310Button;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.shuffleboard.*;

public class RobotContainer
{
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final ControlsFactory _controlsFactory;
    private final LiveWindow liveWindowTab = new LiveWindow(drivebase, intake);
    private final RawData rawDataTab = new RawData(drivebase, intake);

    public RobotContainer()
    {
        final var driver = new Joystick(0);
        final var operator = new Joystick(1);
        _controlsFactory = new ControlsFactory(driver, operator);

        BindCommands();

        //TODO: Check shuffleboard config to change drive type. Also make it check periodically
        drivebase.setDefaultCommand(new ManualTankDrive(drivebase, _controlsFactory));
    }

    private void BindCommands()
    {
        // new JoystickButton(operator, 1).whenPressed(new CloseHatchFingers(hatch));
        // new JoystickButton(operator, 2).whenPressed(new OpenHatchFingers(hatch));
        // new JoystickButton(operator, 3).whenPressed(new ExtendHatchArm(hatch));
        // new JoystickButton(operator, 4).whenPressed(new RetractHatchArm(hatch));
        // Example: When operator joystick presses A or B, do something
        // new JoystickButton(operator, 1).whenPressed(new ExtendElevator(elevator));
        // new JoystickButton(operator, 2).whenPressed(new RetractElevator(elevator));

        // TODO: Instead of using a joystick button put this in shuffleboard button.
        _controlsFactory.createButton(Controller.Driver, Logitech310Button.A).whenPressed(getAutonomousCommand());
    }

    public Command getAutonomousCommand() {
        // TODO: Run frc-frc-characterization to get these values
        // final var ksVolts = 0.22;
        // final var kvVoltSecondsPerMeter = 1.98;
        // final var kaVoltSecondsSquaredPerMeter = 0.2;
        // final var motorFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

        // final double kTrackwidthMeters = 0.69; // TODO: Measure the distance between the wheels.
        // final var kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

        // final var exampleTrajectory = getTrajectory(kDriveKinematics, motorFeedforward);

        // TODO: Run frc-frc-characterization to get these values
        // final var kPDriveVel = 8.5;
        // final var kRamseteB = 2.0; // I think this can be fixed.
        // final var kRamseteZeta = 0.7; // I think this can be fixed.
        // final var ramseteCommand = new RamseteCommand(
        //     exampleTrajectory,
        //     drivebase::getPose,
        //     new RamseteController(kRamseteB, kRamseteZeta),
        //     motorFeedforward,
        //     kDriveKinematics,
        //     drivebase::getWheelSpeeds,
        //     new PIDController(kPDriveVel, 0, 0),
        //     new PIDController(kPDriveVel, 0, 0),
        //     // RamseteCommand passes volts to the callback, TODO: are the params swaped?
        //     drivebase::tankDriveVolts,
        //     drivebase
        // );

        // // Run path following command, then stop at the end.
        // return ramseteCommand.andThen(() -> drivebase.tankDriveVolts(0, 0));
        return null;
    }

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
