package frc.robot.autonomous.modes;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.autonomous.*;
import frc.robot.autonomous.commands.DrivePath;
import frc.robot.subsystems.drivebase.*;

public class ShootThenBackup extends AutoModeBase {
    private final DrivebaseSubsystem _drivebase;

    public ShootThenBackup(RobotContainer robotContainer) {
        super("Shoot Then Backup");
        addRequirements(robotContainer.drivebase);
        _drivebase = robotContainer.drivebase;

        final var holdDriveBase = new WaitCommand(1.0);
        holdDriveBase.addRequirements(_drivebase);

        this.addCommands(new DrivePath(_drivebase, false));
        this.addCommands(new InstantCommand(() -> _drivebase.setPower(0.0, 0.0)));
        this.addCommands(holdDriveBase);
    }

    @Override
    public void initialize() {
        super.initialize();
        _drivebase.setBreakMode(true);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        // _drivebase.setBreakMode(false);
    }
}
