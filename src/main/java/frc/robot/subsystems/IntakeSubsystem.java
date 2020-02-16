package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.libs.components.*;
import frc.robot.*;

public class IntakeSubsystem extends RoyalSubsystem {
    private final WPI_TalonSRX _ballIn;
    private final WPI_TalonSRX _upstream;
    private final BallSensor _ballSensor;

    public IntakeSubsystem() {
        _ballIn = Components.Intake.ballIn;
        _upstream = Components.Intake.upstream;
        _ballSensor = new BallSensor(0);
    }

    public void setIntakePower(double power) {
        _ballIn.set(ControlMode.PercentOutput, power);
    }

    public void setUpstreamPower(double power) {
        _upstream.set(ControlMode.PercentOutput, power);
    }
}
