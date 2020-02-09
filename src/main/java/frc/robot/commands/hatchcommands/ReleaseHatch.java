package frc.robot.commands.hatchcommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class ReleaseHatch extends SequentialCommandGroup
{
    private HatchSubsystem _hatch;

    public ReleaseHatch(HatchSubsystem subsystem)
    {
        _hatch = subsystem;

        this.addCommands(new CloseHatchFingers(_hatch),
                         new RetractHatchArm(_hatch),
                         new OpenHatchFingers(_hatch));
    }
}
