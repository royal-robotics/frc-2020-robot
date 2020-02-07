package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class RoyalSubsystem extends SubsystemBase
{
    private final Notifier _controlLoop;

    public RoyalSubsystem()
    {
        _controlLoop = new Notifier(() -> controlLoop());
    }

    protected void startDashboardLoop() {
        final double ControlLoopIntervalMs = 100.0;
        _controlLoop.startPeriodic(ControlLoopIntervalMs / 1000.0);
    }

    protected void controlLoop() {

    }
}
