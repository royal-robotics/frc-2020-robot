package frc.robot.shuffleboard;

import frc.robot.subsystems.*;
import frc.robot.subsystems.drivebase.*;

public class RawDataTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final Intake _intake;

    public RawDataTab(DrivebaseSubsystem drivebaseSubsystem, Intake intake) {
        super("Driver");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intake = intake;

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
