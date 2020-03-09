package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.RobotContainer;
import frc.robot.autonomous.modes.*;

public class AutoModeSelector {
    private final SendableChooser<AutoModeBase> _chooser;

    public AutoModeSelector(RobotContainer robotContainer) {
        // TODO: Get the string key from the AutoMode name
        _chooser = new SendableChooser<AutoModeBase>();
        _chooser.setDefaultOption("Shoot Then Backup", new ShootThenBackup(robotContainer));
        _chooser.addOption("Do Nothing", new DoNothingAutoMode());
        SmartDashboard.putData("Auto Selector", _chooser);
    }

    public AutoModeBase getAutoMode() {
        return _chooser.getSelected();
    }
}
