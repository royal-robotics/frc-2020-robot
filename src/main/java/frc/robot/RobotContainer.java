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
    private final ExampleSubsystem subsystem = new ExampleSubsystem();

    public RobotContainer()
    {
        BindCommands();

        drivebase.setDefaultCommand(new ManualDrive(drivebase, driver));
    }

    private void BindCommands()
    {
        // Example: When operator joystick presses A or B, do something
        new JoystickButton(operator, 1).whenPressed(new ExtendElevator(elevator));
        new JoystickButton(operator, 2).whenPressed(new RetractElevator(elevator));
    }
}
