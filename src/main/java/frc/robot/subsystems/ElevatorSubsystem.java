package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.libs.components.*;
import frc.robot.Components;

public final class ElevatorSubsystem extends SubsystemBase
{
    private final MotorGroup elevator_motors;

    public ElevatorSubsystem()
    {
        elevator_motors = new MotorGroup (Components.Drivebase.elevator_motor);
    }

    public void SetElevatorHeight(double distance)
    {
        elevator_motors.setPosition(distance);
    }
}
