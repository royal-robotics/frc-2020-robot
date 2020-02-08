package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ReleaseHatch extends CommandBase
{
    private HatchSubsystem _hatch;

    public ReleaseHatch(HatchSubsystem subsystem)
    {
        _hatch = subsystem;
    }

    @Override
    public void execute()
    {
        _hatch.closeFingers();
        _hatch.retractArm();
        _hatch.openFingers();
    }
}
