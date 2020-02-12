package frc.robot.shuffleboard;

import edu.wpi.first.networktables.*;
import frc.robot.subsystems.*;

public class LiveWindow extends RoyalShuffleTab {
    private final NetworkTable table;
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public LiveWindow(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        table = NetworkTableInstance.getDefault().getTable("LiveWindow");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void periodic() {

    }

}
