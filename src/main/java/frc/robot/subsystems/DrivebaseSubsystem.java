package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.examplecode.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class DrivebaseSubsystem extends SubsystemBase
{
    private final Neomotor left_motor1;
    private final Neomotor left_motor2;
    private final Neomotor right_motor1;
    private final Neomotor right_motor2;

    public DrivebaseSubsystem(int left_motor1_id, int left_motor2_id, int right_motor1_id, int right_motor2_id)
    {
        left_motor1 = new Neomotor(left_motor1_id, MotorType.kBrushless);
        left_motor2 = new Neomotor(left_motor2_id, MotorType.kBrushless);
        right_motor1 = new Neomotor(right_motor1_id, MotorType.kBrushless);
        right_motor2 = new Neomotor(right_motor2_id, MotorType.kBrushless);
    }

    public void EncoderReset()
    {

    }

    public void TankDrive()
    {

    }
}
