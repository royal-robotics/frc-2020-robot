package frc.robot.subsystems;

import frc.libs.components.*;
import frc.robot.Components;
import frc.robot.*;

public final class DrivebaseSubsystem extends RoyalSubsystem
{
    private final MotorGroup left_motors;
    private final MotorGroup right_motors;

    public DrivebaseSubsystem() {
        left_motors = new MotorGroup (Components.Drivebase.left_motor1, Components.Drivebase.left_motor2);
        right_motors = new MotorGroup (Components.Drivebase.right_motor1, Components.Drivebase.right_motor2);
    }

    public double getLeftVelocity() {
        return left_motors.getVelocity();
    }

    public double getLeftPosition(){
        return left_motors.getPosition();
    }

    public double getRightVelocity(){
        return right_motors.getVelocity();
    }

    public double getRightPosition(){
        return right_motors.getPosition();
    }

    public void EncoderPositionReset()
    {
        left_motors.resetPosition();
        right_motors.resetPosition();
    }

    // Pass in power values without inverting
    public void Move(double leftPower, double rightPower)
    {
        left_motors.setSpeed(-leftPower);
        right_motors.setSpeed(rightPower);
    }
}
