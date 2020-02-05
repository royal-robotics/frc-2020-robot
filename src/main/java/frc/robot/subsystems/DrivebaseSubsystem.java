package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.libs.components.*;
import frc.robot.Components;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class DrivebaseSubsystem extends SubsystemBase
{
    private final MotorGroup left_motors;
    private final MotorGroup right_motors;

    public DrivebaseSubsystem()
    {
        left_motors = new MotorGroup (new Neomotor(Components.Drivebase.left_motor1),
                        new Neomotor(Components.Drivebase.left_motor2));
        right_motors = new MotorGroup (new Neomotor(Components.Drivebase.right_motor1),
                        new Neomotor(Components.Drivebase.right_motor2));
    }

    public void EncoderPositionReset()
    {
        left_motors.ResetPositions();
        right_motors.ResetPositions();
    }

    public void TankDrive()
    {

    }
}
