package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.libs.components.*;
import frc.robot.Components;

public final class DrivebaseSubsystem extends SubsystemBase
{
    private final static double deadband = 0.1;
    private final MotorGroup left_motors;
    private final MotorGroup right_motors;

    public DrivebaseSubsystem()
    {
        left_motors = new MotorGroup (Components.Drivebase.left_motor1, Components.Drivebase.left_motor2);
        right_motors = new MotorGroup (Components.Drivebase.right_motor1, Components.Drivebase.right_motor2);
    }

    public void EncoderPositionReset()
    {
        left_motors.resetPosition();
        right_motors.resetPosition();
    }

    // Pass in axis values without inverting
    public void Move(double left_joystick, double right_joystick)
    {
        if (left_joystick > deadband || left_joystick < -deadband)
        {
            left_motors.setSpeed(-left_joystick);
        }
        else
        {
            left_motors.setSpeed(0);
        }

        if (right_joystick > deadband || right_joystick < -deadband)
        {
            right_motors.setSpeed(right_joystick);
        }
        else
        {
            right_motors.setSpeed(0);
        }
    }
}
