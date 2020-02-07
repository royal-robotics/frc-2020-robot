package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;

public final class DrivebaseSubsystem extends RoyalSubsystem
{
    private final static double deadband = 0.1;
    private final MotorGroup left_motors;
    private final MotorGroup right_motors;


    public DrivebaseSubsystem()
    {
        super();
        left_motors = new MotorGroup (Components.Drivebase.left_motor1, Components.Drivebase.left_motor2);
        right_motors = new MotorGroup (Components.Drivebase.right_motor1, Components.Drivebase.right_motor2);
        EncoderPositionReset();

        this.startDashboardLoop();
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

    @Override
    protected void controlLoop()
    {
        // DataDisplay.Update("Raw Data", "Left motors velocity", left_motors.getVelocity());
        // DataDisplay.Update("Raw Data", "Left motors position", left_motors.getPosition());
        // DataDisplay.Update("Raw Data", "Right motors velocity", right_motors.getVelocity());
        // DataDisplay.Update("Raw Data", "Right motors position", right_motors.getPosition());
    }
}
