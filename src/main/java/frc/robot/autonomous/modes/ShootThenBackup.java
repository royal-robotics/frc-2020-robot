package frc.robot.autonomous.modes;

import frc.robot.*;
import frc.robot.autonomous.*;
import frc.robot.commands.drivebase.*;
import frc.robot.commands.shooter.*;
import frc.robot.subsystems.drivebase.*;

public class ShootThenBackup extends AutoModeBase {
    private final DrivebaseSubsystem _drivebase;

    public ShootThenBackup(RobotContainer robotContainer) {
        super("Shoot Then Backup");
        _drivebase = robotContainer.drivebase;

        this.addCommands(new TargetAndShoot(robotContainer.intake, robotContainer.shooter));
        this.addCommands(new DrivePath(_drivebase, false).andThen(() -> _drivebase.setPower(0.0, 0.0)));
    }
}
