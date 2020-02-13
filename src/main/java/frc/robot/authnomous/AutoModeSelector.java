package frc.robot.authnomous;

public class AutoModeSelector {
    public AutoModeSelector() {

    }

    public AutoModeBase getAutoMode() {
        return new ExampleAutoMode();
    }
}
