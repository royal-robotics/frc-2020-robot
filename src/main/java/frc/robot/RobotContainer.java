package frc.robot;

import frc.robot.commands.manual.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.climber.*;
import frc.robot.shuffleboard.*;

public class RobotContainer {
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final TurretSubsystem turret = new TurretSubsystem();
    private final ClimberSubsystem climber = new ClimberSubsystem();

    private final DriverTab driverTab = new DriverTab(drivebase, intake);
    private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {
        //TODO: Check shuffleboard config to change drive type. Also make it update periodically
        drivebase.setDefaultCommand(new ManualTankDrive(drivebase));
        intake.setDefaultCommand(new IntakeControl(intake));
        turret.setDefaultCommand(new TurretControl(turret));
        climber.setDefaultCommand(new ClimberControl(climber));
    }
}
