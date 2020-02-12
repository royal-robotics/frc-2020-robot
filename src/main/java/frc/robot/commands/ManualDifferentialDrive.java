package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ManualDifferentialDrive extends CommandBase
{
    private final DrivebaseSubsystem _drivebase;
    private final Joystick _driver; // TODO: Make the Joysticks private to a special ControlsFactory class.

    public ManualDifferentialDrive(DrivebaseSubsystem subsystem, Joystick driver) {
        _drivebase = subsystem;
        _driver = driver;
        addRequirements(_drivebase);
    }

	@Override
    public void execute()
    {
        final double deadband = 0.1;
        double throttle = _driver.getRawAxis(1); // Left stick Y axis
        double turn = _driver.getRawAxis(4); // Right stick X axis

        if (throttle < deadband || throttle > -deadband) {
            throttle = 0;
        }
        if (turn < deadband || turn > -deadband) {
            turn = 0;
        }

        double turnPower = turn * 0.75;

        _drivebase.setPower(throttle + turnPower, throttle - turnPower);
    }
}
