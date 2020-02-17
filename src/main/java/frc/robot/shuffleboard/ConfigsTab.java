package frc.robot.shuffleboard;

import frc.robot.subsystems.*;
import frc.robot.subsystems.drivebase.*;

public class ConfigsTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public ConfigsTab(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        super("Configs");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;

        this.initialize();
    }

    @Override
    public void periodic() {

    }
}
