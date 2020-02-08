package frc.robot.subsystems;

import frc.robot.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.*;

public class HatchSubsystem extends RoyalSubsystem
{
    private DoubleSolenoid _carriageFingers;
    private DoubleSolenoid _carriageArm;

    public HatchSubsystem()
    {
        _carriageFingers = Components.HatchManipulator.carriageFingers;
        _carriageArm = Components.HatchManipulator.carriageArm;
    }

    public void openFingers()
    {
        _carriageFingers.set(Value.kForward);
    }

    public void closeFingers()
    {
        _carriageFingers.set(Value.kReverse);
    }

    public void pushArm()
    {
        _carriageArm.set(Value.kForward);
    }

    public void retractArm()
    {
        _carriageArm.set(Value.kReverse);
    }
}
