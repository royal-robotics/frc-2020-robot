package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ExampleCommand extends CommandBase
{
    private final ExampleSubsystem subsystem;

    public ExampleCommand(ExampleSubsystem subsystem)
    {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute()
    {
    }
}
