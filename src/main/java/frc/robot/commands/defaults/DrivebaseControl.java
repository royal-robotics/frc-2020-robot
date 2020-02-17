package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Controls;
import frc.robot.subsystems.drivebase.*;

public class DrivebaseControl extends CommandBase {
    private final DrivebaseSubsystem _drivebase;

    public DrivebaseControl(DrivebaseSubsystem driverbase) {
        _drivebase = driverbase;
        addRequirements(_drivebase);
    }

	@Override
    public void execute() {
        final var tankThrottleValues = Controls.DriveBase.getThrottleValues();
        _drivebase.setPower(tankThrottleValues.left, tankThrottleValues.right);
    }
}
