package examplecode;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Neomotor
{
    private CANSparkMax motor;
    private CANEncoder encoder;

    public Neomotor(int id, MotorType type)
    {
        motor = new CANSparkMax(id, type);
        encoder = motor.getEncoder();
    }

    public void move(double direction)
    {
        double speed = 1;
        if (direction > 0.1 || direction < -0.1)
        {
            motor.set(speed * direction);
        }
        else
        {
            motor.set(0);
        }
    }
}