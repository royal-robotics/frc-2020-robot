package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class OldManualDrive extends CommandBase
{
    private final OldDrivebaseSubsystem drivebase;
    private final Joystick driver;

    public OldManualDrive(OldDrivebaseSubsystem subsystem, Joystick driver)
    {
        drivebase = subsystem;
        this.driver = driver;
        addRequirements(drivebase);
    }

	@Override
    public void execute()
    {
        drivebase.setPower(driver.getRawAxis(1), driver.getRawAxis(5));
    }
}
