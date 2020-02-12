package frc.robot.shuffleboard;

import edu.wpi.first.networktables.*;
import frc.robot.subsystems.*;

public class RawData extends RoyalShuffleTab {
    private final NetworkTable table;
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public RawData(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        table = NetworkTableInstance.getDefault().getTable("RawData");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;

        // tart
        this.initialize();
    }

    @Override
    public void periodic() {
    //    table.getEntry("Right Motors Velocity").setDouble(_drivebaseSubsystem.getRightVelocity());
    //    table.getEntry("Right Motors Position").setDouble(_drivebaseSubsystem.getRightPosition());
    //    table.getEntry("Left Motors Velocity").setDouble(_drivebaseSubsystem.getLeftVelocity());
    //    table.getEntry("Left Motors Position").setDouble(_drivebaseSubsystem.getLeftPosition());
    }

}
