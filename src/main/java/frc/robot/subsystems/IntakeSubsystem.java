package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.*;

public class IntakeSubsystem extends RoyalSubsystem {
    private final BallSensor _ballSensor;

    public IntakeSubsystem() {
        _ballSensor = new BallSensor(0);
    }

    public int getNumBalls() {
        return 0;
    }
}
