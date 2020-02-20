package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Controls;
import frc.robot.subsystems.drivebase.*;

public class DrivebaseDefault extends CommandBase {
    private final DrivebaseSubsystem _drivebase;

    public DrivebaseDefault(DrivebaseSubsystem drivebase) {
        addRequirements(drivebase);
        _drivebase = drivebase;
    }

	@Override
    public void execute() {
        final var tankThrottleValues = Controls.DriveBase.getThrottleValues();
        _drivebase.setPower(tankThrottleValues.left, tankThrottleValues.right);
    }
}
