package frc.robot.autonomous;

import frc.robot.autonomous.modes.*;

public class AutoModeSelector {
    public AutoModeSelector() {

    }

    public AutoModeBase getAutoMode() {
        return new ExampleAutoMode();
    }
}
