package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.*;

public class AutoModeBase extends SequentialCommandGroup {
    private final String _name;

    public AutoModeBase(String name) {
        super();
        _name = name;
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.printf("Starting Auto: %s\n", _name);
    }
}
