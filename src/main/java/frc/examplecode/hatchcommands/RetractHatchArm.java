package frc.examplecode.hatchcommands;
import frc.examplecode.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class RetractHatchArm extends CommandBase
{
    private HatchSubsystem _hatch;

    public RetractHatchArm(HatchSubsystem subsystem)
    {
        _hatch = subsystem;
    }

    @Override
    public void execute()
    {
        _hatch.retractArm();
    }

    @Override
    public boolean isFinished()
    {
        if (_hatch.armPosition() == Value.kReverse)
        {
            return true;
        }
        return false;
    }
}
