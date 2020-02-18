package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.autonomous.*;

public class Robot extends TimedRobot {
    private final RobotContainer _robotContainer;
    private final AutoModeSelector _autoModeSelector;

    private AutoModeBase _autoMode;

    public Robot() {
        _robotContainer = new RobotContainer();
        _autoModeSelector = new AutoModeSelector();
    }

    @Override
    public void robotInit() {
    }

    @Override
    public void autonomousInit() {
        _autoMode = _autoModeSelector.getAutoMode();
        _autoMode.schedule();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Shuffleboard.update();
    }

    @Override
    public void disabledInit() {
        _robotContainer.storeState();
    }
}
