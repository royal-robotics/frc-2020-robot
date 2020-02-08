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

    @Override
    public void AddTableEntries()
    {
        DataTable.MakeBooleanEntry("Raw Data", "Ball Sensor");
    }

    @Override
    protected void UpdateTableEntries()
    {
        DataTable.Update("Raw Data", "Ball Sensor", ball_sensor.isBallDetected());
    }
}
