package frc.robot.shuffleboard;

import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.intake.*;

public class ConfigsTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final Intake _intake;

    public ConfigsTab(DrivebaseSubsystem drivebaseSubsystem, Intake intake) {
        super("Configs");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intake = intake;

        this.initialize();
    }

    @Override
    public void periodic() {

    }
}
