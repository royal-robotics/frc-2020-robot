package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Components;
import frc.robot.subsystems.*;

public class StartConveyor extends CommandBase {
    private final Intake _intake;

    public StartConveyor(Intake intake){
        _intake = intake;
    }

    @Override
    public void initialize() {
        _intake.setConveyorPower(0.8);
    }

    @Override
    public boolean isFinished() {
        return !Components.Intake.getBottomBallSensor();
    }
}
