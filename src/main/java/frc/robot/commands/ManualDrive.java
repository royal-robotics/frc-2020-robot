package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import frc.robot.*;

public class ManualDrive extends CommandBase
{
    private final DrivebaseSubsystem drivebase;
    private final Joystick driver;

    public ManualDrive(DrivebaseSubsystem subsystem, Joystick driver)
    {
        drivebase = subsystem;
        this.driver = driver;
        addRequirements(drivebase);
    }

	@Override
    public void execute()
    {
        drivebase.Move(driver.getRawAxis(1), driver.getRawAxis(5));
    }
}
