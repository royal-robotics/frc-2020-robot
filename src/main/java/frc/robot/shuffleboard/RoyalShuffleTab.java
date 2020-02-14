package frc.robot.shuffleboard;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.shuffleboard.*;

public abstract class RoyalShuffleTab {
    protected final NetworkTable table;
    protected final ShuffleboardTab tab;
    private final Notifier _controlLoop;

    public RoyalShuffleTab(String name) {
        table = NetworkTableInstance.getDefault().getTable(name);
        tab = Shuffleboard.getTab(name);
        _controlLoop = new Notifier(() -> periodic());
    }

    public void initialize(){
        final double ControlLoopIntervalMs = 100.0;
        _controlLoop.startPeriodic(ControlLoopIntervalMs / 1000.0);
    }

    public void periodic() {

    }
}
