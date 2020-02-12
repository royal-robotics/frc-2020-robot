package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ManualTankDrive extends CommandBase
{
    private final DrivebaseSubsystem _drivebase;
    private final Joystick _driver;

    public ManualTankDrive(DrivebaseSubsystem driverbase, Joystick driver)
    {
        _drivebase = driverbase;
        addRequirements(_drivebase);

        _driver = driver;
    }

	@Override
    public void execute()
    {
        double deadband = 0.1;
        double leftPower = _driver.getRawAxis(1);
        double rightPower = _driver.getRawAxis(5);

        if (leftPower < deadband || leftPower > -deadband) {
            leftPower = 0;
        }
        if (rightPower < deadband || rightPower > -deadband) {
            rightPower = 0;
        }

        _drivebase.setPower(leftPower, rightPower);
    }
}
