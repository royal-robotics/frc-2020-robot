package frc.examplecode.hatchcommands;
import frc.examplecode.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class OpenHatchFingers extends CommandBase
{
    private HatchSubsystem _hatch;

    public OpenHatchFingers(HatchSubsystem subsystem)
    {
        _hatch = subsystem;
    }

    @Override
    public void execute()
    {
        _hatch.openFingers();
    }

    @Override
    public boolean isFinished()
    {
        if (_hatch.fingerPosition() == Value.kForward)
        {
            return true;
        }
        return false;
    }
}
