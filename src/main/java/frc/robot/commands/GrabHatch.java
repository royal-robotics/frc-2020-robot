package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class GrabHatch extends CommandBase
{
    private HatchSubsystem _hatch;

    public GrabHatch(HatchSubsystem subsystem)
    {
        _hatch = subsystem;
    }

    @Override
    public void execute()
    {
        _hatch.closeFingers();
        _hatch.pushArm();
        _hatch.openFingers();
    }
}
