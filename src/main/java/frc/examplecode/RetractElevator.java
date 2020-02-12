package frc.examplecode;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

// TODO: Edit this for 2019 robot test
public class RetractElevator extends CommandBase
{
    private final ElevatorSubsystem elevator;

    public RetractElevator(ElevatorSubsystem subsystem)
    {
        elevator = subsystem;
    }

	@Override
    public void execute()
    {
        elevator.SetElevatorHeight(0);
    }
}
