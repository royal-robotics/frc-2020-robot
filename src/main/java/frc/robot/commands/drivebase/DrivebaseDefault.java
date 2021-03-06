package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Controls;
import frc.robot.subsystems.drivebase.*;

public class DrivebaseDefault extends CommandBase {
    private final DrivebaseSubsystem _drivebase;
    private final Button _snailModeEnabled;
    private final Button _ludicrousSpeedEnabled;

    public DrivebaseDefault(DrivebaseSubsystem drivebase) {
        addRequirements(drivebase);
        _drivebase = drivebase;
        _snailModeEnabled = Controls.DriveBase.snailSpeed;
        _ludicrousSpeedEnabled = Controls.DriveBase.ludicrousSpeed;
    }

	@Override
    public void execute() {
        var tankThrottleValues = Controls.DriveBase.getThrottleValues();

        if (_snailModeEnabled.get()) {
            tankThrottleValues = tankThrottleValues.withScaleFactor(0.10);
            _drivebase.setBreakMode(true);
            _drivebase.setPower(tankThrottleValues.left, tankThrottleValues.right);
        } else if (_ludicrousSpeedEnabled.get()) {
            _drivebase.setBreakMode(false);
            _drivebase.setPower(tankThrottleValues.left, tankThrottleValues.right);
        } else {
            tankThrottleValues = tankThrottleValues.withScaleFactor(0.50);
            _drivebase.setBreakMode(false);
            _drivebase.setPower(tankThrottleValues.left, tankThrottleValues.right);
        }
    }
}
