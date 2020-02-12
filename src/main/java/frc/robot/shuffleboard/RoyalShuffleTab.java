package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.Notifier;

public class RoyalShuffleTab {
    private final Notifier _controlLoop;
    public static int x = 0;
    private static int y = 0;
    protected static int z = 0;

    public RoyalShuffleTab() {
        //:( Code
        _controlLoop = new Notifier(() -> periodic());
    }

    public void initialize(){
        final double ControlLoopIntervalMs = 100.0;
        _controlLoop.startPeriodic(ControlLoopIntervalMs / 1000.0);
    }

    public void periodic() {

    }

}
