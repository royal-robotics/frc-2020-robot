package frc.examplecode.hatchcommands;
import frc.examplecode.*;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ExtendHatchArm extends CommandBase
{
    private HatchSubsystem _hatch;

    public ExtendHatchArm(HatchSubsystem subsystem)
    {
        _hatch = subsystem;
    }

    @Override
    public void execute()
    {
        _hatch.extendArm();
    }

    @Override
    public boolean isFinished()
    {
        if (_hatch.armPosition() == Value.kForward)
        {
            return true;
        }
        return false;
    }
}
