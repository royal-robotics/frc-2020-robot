package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;

public final class DrivebaseSubsystem extends RoyalSubsystem
{
    // private final static double deadband = 0.1;
    // private final MotorGroup left_motors;
    // private final MotorGroup right_motors;

    public DrivebaseSubsystem()
    {
        //left_motors = new MotorGroup (Components.Drivebase.left_motor1, Components.Drivebase.left_motor2);
        //right_motors = new MotorGroup (Components.Drivebase.right_motor1, Components.Drivebase.right_motor2);
        // EncoderPositionReset();

        // StartUpdateTableEntries();
    }

    @Override
    public void AddTableEntries()
    {
        DataTable.MakeDoubleEntry("Raw Data", "Left Motors Velocity");
        DataTable.MakeDoubleEntry("Raw Data", "Left Motors Position");
        DataTable.MakeDoubleEntry("Raw Data", "Right Motors Velocity");
        DataTable.MakeDoubleEntry("Raw Data", "Right Motors Position");
    }

    @Override
    protected void UpdateTableEntries()
    {
        // DataTable.Update("Raw Data", "Left Motors Velocity", left_motors.getVelocity());
        // DataTable.Update("Raw Data", "Left Motors Position", left_motors.getPosition());
        // DataTable.Update("Raw Data", "Right Motors Velocity", right_motors.getVelocity());
        // DataTable.Update("Raw Data", "Right Motors Position", right_motors.getPosition());
    }

    public void EncoderPositionReset()
    {
        // left_motors.resetPosition();
        // right_motors.resetPosition();
    }

    // Pass in axis values without inverting
    public void Move(double left_joystick, double right_joystick)
    {
        // if (left_joystick > deadband || left_joystick < -deadband)
        // {
        //     left_motors.setSpeed(-left_joystick);
        // }
        // else
        // {
        //     left_motors.setSpeed(0);
        // }

        // if (right_joystick > deadband || right_joystick < -deadband)
        // {
        //     right_motors.setSpeed(right_joystick);
        // }
        // else
        // {
        //     right_motors.setSpeed(0);
        // }
    }
}
