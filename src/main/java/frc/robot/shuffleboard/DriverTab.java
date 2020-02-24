package frc.robot.shuffleboard;

import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.intake.*;

public class DriverTab extends RoyalShuffleTab {
    private final Drivebase _drivebase;
    private final Intake _intake;

    public DriverTab(Drivebase drivebase, Intake intake) {
        super("Driver");
        _drivebase = drivebase;
        _intake = intake;

        this.initialize();
    }

    @Override
    public void periodic() {

    }

}
