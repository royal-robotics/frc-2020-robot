package frc.robot.shuffleboard;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.subsystems.*;

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
