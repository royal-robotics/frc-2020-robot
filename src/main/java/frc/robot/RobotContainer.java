package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.libs.controls.*;

public class RobotContainer
{
    private static Joystick driver = new Joystick(0);
    private static Joystick operator = new Joystick(1);

    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();

    public RobotContainer()
    {
        drivebase.setDefaultCommand(new ManualDrive(drivebase, driver.getRawAxis(1), driver.getRawAxis(5)));
    }

    private void BindCommands()
    {
        //new JoystickButton(operator, 1).whenPressed();
    }
}
