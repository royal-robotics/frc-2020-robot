package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;

public class IntakeSubsystem extends RoyalSubsystem
{
    private final BallSensor ball_sensor;

    public IntakeSubsystem()
    {
        ball_sensor = new BallSensor(0);

        StartUpdateTableEntries();
    }

    public int getNumBalls()
    {
        return 0;
    }
}
