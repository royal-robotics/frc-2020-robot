package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.shuffleboard.*;

public class RobotContainer
{
    private static Joystick driver = new Joystick(0);
    private static Joystick operator = new Joystick(1);

    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();

    private final LiveWindow liveWindowTab = new LiveWindow(drivebase, intake);
    private final RawData rawDataTab = new RawData(drivebase, intake);

    public RobotContainer()
    {
        BindCommands();

        //TODO: Check shuffleboard config to change drive type. Also make it check periodically
        drivebase.setDefaultCommand(new ManualTankDrive(drivebase, driver));
        //old_drivebase.setDefaultCommand(new OldManualDrive(old_drivebase, driver));
    }

    private void BindCommands()
    {
        // new JoystickButton(operator, 1).whenPressed(new CloseHatchFingers(hatch));
        // new JoystickButton(operator, 2).whenPressed(new OpenHatchFingers(hatch));
        // new JoystickButton(operator, 3).whenPressed(new ExtendHatchArm(hatch));
        // new JoystickButton(operator, 4).whenPressed(new RetractHatchArm(hatch));
    }
}
