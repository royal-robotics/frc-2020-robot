package frc.libs.components;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class MotorGroup
{
    private int number_of_motors;
    public Neomotor[] motors;

    public MotorGroup(Neomotor ...motors)
    {
        number_of_motors = motors.length;
        this.motors = motors;
    }

    public void SetAll(double percentSpeed)
    {
        for (int x = 0; x < motors.length; x++)
        {
            motors[x].set(percentSpeed);
        }
    }

    public void ResetPositions()
    {
        for (int x = 0; x < motors.length; x++)
        {
            motors[x].resetPosition();
        }
    }
}
