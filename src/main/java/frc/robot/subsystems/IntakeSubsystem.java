package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.libs.components.*;
import frc.robot.*;

public class IntakeSubsystem extends RoyalSubsystem {
    private final WPI_TalonSRX _ballIn;
    private final WPI_TalonSRX _ballConvery;
    private final BallSensor _ballSensor;

    public IntakeSubsystem() {
        _ballIn = Components.Intake.ballIn;
        _ballConvery = Components.Intake.conveyer;
        _ballSensor = new BallSensor(0);
    }

    public void autoBallFeed()
    {
        if (_ballSensor.isBallDetected())
        {
            setIntakePower(0.8);
            setConveyerPower(0.8);
        }
        else
        {
            setIntakePower(0);
            setConveyerPower(0);
        }
    }

    public void setIntakePower(double power) {
        _ballIn.set(ControlMode.PercentOutput, power);
    }

    public void setConveyerPower(double power) {
        _ballConvery.set(ControlMode.PercentOutput, power);
    }
}
