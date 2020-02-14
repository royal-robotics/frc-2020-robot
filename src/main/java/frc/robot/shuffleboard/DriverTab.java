package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.Components;
import frc.robot.subsystems.*;

public class DriverTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public DriverTab(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        super("Driver");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;

        tab.add(Components.Camera.limelight).withWidget(BuiltInWidgets.kCameraStream);
        this.initialize();
    }

    @Override
    public void periodic() {

    }

}
