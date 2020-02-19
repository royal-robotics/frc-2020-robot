package frc.robot.shuffleboard;

import frc.robot.subsystems.*;
import frc.robot.subsystems.drivebase.*;

public class DriverTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final Intake _intake;

    public DriverTab(DrivebaseSubsystem drivebaseSubsystem, Intake intake) {
        super("Driver");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intake = intake;

        // tab.add(Components.Camera.limelight).withWidget(BuiltInWidgets.kCameraStream);
        this.initialize();
    }

    @Override
    public void periodic() {

    }

}
