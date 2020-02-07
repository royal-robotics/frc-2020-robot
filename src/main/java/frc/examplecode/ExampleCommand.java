package frc.examplecode;

import edu.wpi.first.wpilibj2.command.*;

// Bind a new instance of this class to a JoystickButton class and it will run the execute
// method when the desired trigger activates (i.e. when button X is pressed)
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
        System.out.println(subsystem.RandomNumber());
    }
}
