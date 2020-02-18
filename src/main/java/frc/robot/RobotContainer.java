package frc.robot;

import frc.robot.commands.defaults.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.climber.*;
import frc.robot.subsystems.colorwheel.*;
import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.shooter.*;
import frc.robot.shuffleboard.*;

public class RobotContainer {
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final Shooter shooter = new Shooter();
    // private final ClimberSubsystem climber = new ClimberSubsystem();
    // private final ColorWheelSubsystem colorWheel = new ColorWheelSubsystem();

    // private final DriverTab driverTab = new DriverTab(drivebase, intake);
    // private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    // private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {

        // Calibrate the gyro once when the robot turns on.
        // It's very important the robot is motionless until
        // this calibration is complete.
        Components.Drivebase.gyro.calibrate();
        Components.Drivebase.gyro.reset();

        drivebase.setDefaultCommand(new DrivebaseControl(drivebase));
        intake.setDefaultCommand(new IntakeControl(intake));
        shooter.bindDefaultCommands();
    }

    public final void storeState() {
        shooter.turret.save();
        shooter.hood.save();
    }
}
