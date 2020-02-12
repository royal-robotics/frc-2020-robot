package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ManualDifferentialDrive extends CommandBase
{
    private final DrivebaseSubsystem drivebase;
    private final Joystick driver;

    public ManualDifferentialDrive(DrivebaseSubsystem subsystem, Joystick driver)
    {
        drivebase = subsystem;
        this.driver = driver;
        addRequirements(drivebase);
    }

	@Override
    public void execute()
    {
        double deadband = 0.1;
        double throttle = driver.getRawAxis(1); // Left stick Y axis
        double turn = driver.getRawAxis(4); // Right stick X axis

        if (throttle < deadband || throttle > -deadband) {
            throttle = 0;
        }
        if (turn < deadband || turn > -deadband) {
            turn = 0;
        }

        double turnPower = turn * 0.75;

        drivebase.Move(throttle + turnPower, throttle - turnPower);
    }
}
