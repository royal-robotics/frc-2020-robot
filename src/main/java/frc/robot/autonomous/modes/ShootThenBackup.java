package frc.robot.autonomous.modes;

import edu.wpi.first.wpilibj2.command.*;
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

        final var targetAndShoot = new TargetAndShoot(robotContainer.intake, robotContainer.shooter);
        final var shootWithTimeout = new ParallelRaceGroup(targetAndShoot, new WaitCommand(10.0));
        final var driveStraightAndStop = new DriveStraight(_drivebase, 2.0, false).andThen(() -> _drivebase.setPower(0.0, 0.0));
        this.addCommands(shootWithTimeout, driveStraightAndStop);
    }
}
