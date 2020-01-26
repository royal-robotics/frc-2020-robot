package examplecode;

import edu.wpi.first.wpilibj.PWM;

public class Revservo
{
    private PWM servo;

    public Revservo(int channel)
    {
        servo = new PWM(channel);
    }

    public void Move(double direction)
    {
        if(direction > 0.1){   
            servo.setSpeed(1);
        }
        else if (direction < -0.1)
        {
            servo.setSpeed(-1);
        }
        else {
            servo.setSpeed(0);
        }
    }
}