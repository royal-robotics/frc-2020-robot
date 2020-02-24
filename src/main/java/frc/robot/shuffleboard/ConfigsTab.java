package frc.robot.shuffleboard;

import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.intake.*;

public class ConfigsTab extends RoyalShuffleTab {
    private final Drivebase _drivebase;
    private final Intake _intake;

    public ConfigsTab(Drivebase drivebase, Intake intake) {
        super("Configs");
        _drivebase = drivebase;
        _intake = intake;

        this.initialize();
    }

    @Override
    public void periodic() {

    }
}
