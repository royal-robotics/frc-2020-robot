package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ManualTankDrive extends CommandBase
{
    private final DrivebaseSubsystem drivebase;
    private final Joystick driver;

    public ManualTankDrive(DrivebaseSubsystem subsystem, Joystick driver)
    {
        drivebase = subsystem;
        this.driver = driver;
        addRequirements(drivebase);
    }

	@Override
    public void execute()
    {
        double deadband = 0.1;
        double leftPower = driver.getRawAxis(1);
        double rightPower = driver.getRawAxis(5);

        if (leftPower < deadband || leftPower > -deadband) {
            leftPower = 0;
        }
        if (rightPower < deadband || rightPower > -deadband) {
            rightPower = 0;
        }

        drivebase.Move(leftPower, rightPower);
    }
}
