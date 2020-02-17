package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import frc.robot.*;

public class IntakeSubsystem extends RoyalSubsystem {
    private final WPI_TalonSRX _ballIn;
    private final WPI_TalonSRX _ballConvery;
    private final DigitalInput _ballSensor;

    private boolean _autoBallIntakeEnabled = false;

    public IntakeSubsystem() {
        _ballIn = Components.Intake.ballIn;
        _ballConvery = Components.Intake.conveyer;
        _ballSensor = Components.Intake.ballSensor;
    }

    public void setIntakePower(double power) {
        _ballIn.set(ControlMode.PercentOutput, power);
    }

    public void setConveyerPower(double power) {
        _ballConvery.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void periodic() {
        if (_autoBallIntakeEnabled) {
            if (_ballSensor.get()) {
                setIntakePower(0.8);
                setConveyerPower(0.8);
            } else {
                setIntakePower(0);
                setConveyerPower(0);
            }
        }
    }
}
