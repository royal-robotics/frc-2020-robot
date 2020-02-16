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
    private final ControlsFactory _controlsFactory;
    private final DriverTab driverTab = new DriverTab(drivebase, intake);
    private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {
        final var driver = new Joystick(0);
        final var operator = new Joystick(1);
        _controlsFactory = new ControlsFactory(driver, operator);

        bindCommands();

        //TODO: Check shuffleboard config to change drive type. Also make it update periodically
        drivebase.setDefaultCommand(new ManualTankDrive(drivebase, _controlsFactory));
        intake.setDefaultCommand(new IntakeControl(intake, _controlsFactory));
    }

    private void bindCommands() {
    }
}
