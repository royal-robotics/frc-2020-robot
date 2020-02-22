package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.Intake;

public class StopAllIntake extends CommandBase {
    private final Intake _intake;

    public StopAllIntake(Intake intake) {
        _intake = intake;
    }

    @Override
    public void initialize() {
        _intake.setIntakePower(0.0);
        _intake.setConveyorPower(0.0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
