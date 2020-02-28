package frc.robot.autonomous;

import frc.robot.RobotContainer;
import frc.robot.autonomous.modes.*;

public class AutoModeSelector {
    // private final SendableChooser<AutoModeBase> _chooser;
    private final AutoModeBase _fixedCommand;

    public AutoModeSelector(RobotContainer robotContainer) {
        // _chooser = new SendableChooser<AutoModeBase>();
        // _chooser.setDefaultOption("Do Nothing", new NoopAutoMode("Do Nothing"));
        // _chooser.addOption("Test Option", new NoopAutoMode("Test Auto Mode"));
        // _chooser.addOption("Test Drive", new DriveTestMode(robotContainer));
        _fixedCommand = new ShootThenBackup(robotContainer);
    }

    public AutoModeBase getAutoMode() {
        // return _chooser.getSelected();
        return _fixedCommand;
    }
}
