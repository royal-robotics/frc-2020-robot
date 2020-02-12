package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.Notifier;

public abstract class RoyalShuffleTab {
    private final Notifier _controlLoop;

    public RoyalShuffleTab() {
        _controlLoop = new Notifier(() -> periodic());
    }

    public void initialize(){
        final double ControlLoopIntervalMs = 100.0;
        _controlLoop.startPeriodic(ControlLoopIntervalMs / 1000.0);
    }

    public void periodic() {

    }
}
