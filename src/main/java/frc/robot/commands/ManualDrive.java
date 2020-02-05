package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ManualDrive extends CommandBase
{
    private final DrivebaseSubsystem drivebase;
    private final double left_speed;
    private final double right_speed;

    public ManualDrive(DrivebaseSubsystem subsystem, double left_speed, double right_speed)
    {
        drivebase = subsystem;
        this.left_speed = left_speed;
        this.right_speed = right_speed;
        addRequirements(drivebase);
    }

	@Override
    public void execute()
    {
        drivebase.Move(left_speed, right_speed);
    }
}
