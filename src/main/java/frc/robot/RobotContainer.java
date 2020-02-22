package frc.robot;

import frc.robot.commands.climber.ElevatorDefault;
import frc.robot.commands.colorwheel.*;
import frc.robot.commands.drivebase.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.TurretDefault;
import frc.robot.subsystems.*;
import frc.robot.subsystems.climber.*;
import frc.robot.subsystems.colorwheel.*;
import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.shooter.*;
import frc.robot.shuffleboard.*;

public class RobotContainer {
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();
    private final Climber climber = new Climber();
    private final ColorWheelSubsystem colorWheel = new ColorWheelSubsystem();

    // private final DriverTab driverTab = new DriverTab(drivebase, intake);
    // private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    // private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {
        // Calibrate the gyro once when the robot turns on.
        // It's very important the robot is motionless until
        // this calibration is complete.
        Components.Drivebase.gyro.calibrate();
        Components.Drivebase.gyro.reset();

        drivebase.setDefaultCommand(new DrivebaseDefault(drivebase));
        intake.setDefaultCommand(new IntakeDefault(intake));
        colorWheel.setDefaultCommand(new ColorWheelDefault(colorWheel));
        shooter.turret.setDefaultCommand(new TurretDefault(shooter.turret));
        climber.elevator.setDefaultCommand(new ElevatorDefault(climber.elevator));
    }

    public final void storeState() {
        shooter.turret.save();
        shooter.hood.save();
    }
}
