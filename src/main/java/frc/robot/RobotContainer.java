package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer
{
    private static Joystick driver = new Joystick(0);
    private static Joystick operator = new Joystick(1);

    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    private final ElevatorSubsystem elevator = new ElevatorSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();

    private final HatchSubsystem hatch = new HatchSubsystem();
    private final OldDrivebaseSubsystem old_drivebase = new OldDrivebaseSubsystem();

    public RobotContainer()
    {
        BindCommands();

        //drivebase.setDefaultCommand(new ManualDrive(drivebase, driver));
        old_drivebase.setDefaultCommand(new OldManualDrive(old_drivebase, driver));
    }

    private void BindCommands()
    {
        new JoystickButton(operator, 1).whenHeld(new GrabHatch(hatch));
        new JoystickButton(operator, 1).whenReleased(new ReleaseHatch(hatch));
    }
}
