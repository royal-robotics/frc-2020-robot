package frc.robot.shuffleboard;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.subsystems.*;

public class RawDataTab extends RoyalShuffleTab {
    private final DrivebaseSubsystem _drivebaseSubsystem;
    private final IntakeSubsystem _intakeSubsystem;

    public RawDataTab(DrivebaseSubsystem drivebaseSubsystem, IntakeSubsystem intakeSubsystem) {
        super("Driver");
        _drivebaseSubsystem = drivebaseSubsystem;
        _intakeSubsystem = intakeSubsystem;

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
