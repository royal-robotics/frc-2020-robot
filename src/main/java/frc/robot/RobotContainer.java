package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer
{
    private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();

    public RobotContainer()
    {
        //TODO: Fill in TankDrive with actual Joystick buttons
        drivebase.setDefaultCommand(new RunCommand(() -> drivebase.TankDrive()));
    }

    public Command GetAutonomousCommand()
    {
        return new InstantCommand();
    }
}
