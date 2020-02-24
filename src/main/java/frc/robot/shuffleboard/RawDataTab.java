package frc.robot.shuffleboard;

import frc.robot.subsystems.intake.*;
import frc.robot.subsystems.drivebase.*;

public class RawDataTab extends RoyalShuffleTab {
    private final Drivebase _drivebase;
    private final Intake _intake;

    public RawDataTab(Drivebase drivebase, Intake intake) {
        super("Driver");
        _drivebase = drivebase;
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
