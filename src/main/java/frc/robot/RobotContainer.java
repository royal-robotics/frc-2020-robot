package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import frc.libs.controls.ControlsFactory;
import frc.robot.commands.manual.*;
import frc.robot.subsystems.*;
import frc.robot.shuffleboard.*;

public class RobotContainer {
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final TurretSubsystem turret = new TurretSubsystem();
    private final DriverTab driverTab = new DriverTab(drivebase, intake);
    private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {

        bindCommands();

        //TODO: Check shuffleboard config to change drive type. Also make it update periodically
        drivebase.setDefaultCommand(new ManualTankDrive(drivebase));
        intake.setDefaultCommand(new IntakeControl(intake));
        turret.setDefaultCommand(new TurretControl(turret));
    }

    private void bindCommands() {
    }
}
