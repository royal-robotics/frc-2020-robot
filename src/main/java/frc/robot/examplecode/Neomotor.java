package examplecode;

import edu.wpi.first.wpilibj.smartdashboard.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Neomotor
{
    private CANSparkMax motor;
    private CANEncoder encoder;
    private int motor_id;

    public Neomotor(int id, MotorType type)
    {
        motor = new CANSparkMax(id, type);
        encoder = motor.getEncoder();
        motor_id = id;
    }

    public void Move(double direction)
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

    public void ReadEncoder()
    {
        SmartDashboard.putNumber("Encoder Position#"+Integer.toString(motor_id), encoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity#"+Integer.toString(motor_id), encoder.getVelocity());
    }
}