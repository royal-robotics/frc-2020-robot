package frc.libs.components;

import com.revrobotics.CANSparkMax;

public class MotorGroup extends Neomotor
{
    public MotorGroup(CANSparkMax leader, CANSparkMax ...followers)
    {
        super(leader);

        for(int x = 0; x < followers.length; x++)
        {
            followers[x].follow(leader);
        }
    }
}
