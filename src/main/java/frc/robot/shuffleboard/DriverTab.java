package frc.robot.shuffleboard;

import frc.robot.subsystems.*;
import frc.robot.subsystems.drivebase.*;

public class DriverTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public DriverTab(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        super("Driver");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;

        // tab.add(Components.Camera.limelight).withWidget(BuiltInWidgets.kCameraStream);
        this.initialize();
    }

    @Override
    public void periodic() {

    }

}
