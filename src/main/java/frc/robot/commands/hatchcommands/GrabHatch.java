package frc.robot.commands.hatchcommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class GrabHatch extends SequentialCommandGroup
{
    private HatchSubsystem _hatch;

    public GrabHatch(HatchSubsystem subsystem)
    {
        _hatch = subsystem;

        this.addCommands(new CloseHatchFingers(_hatch),
                         new ExtendHatchArm(_hatch),
                         new OpenHatchFingers(_hatch));
    }
}
