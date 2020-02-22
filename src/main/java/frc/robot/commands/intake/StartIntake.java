package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Components;
import frc.robot.subsystems.Intake;

public class StartIntake extends CommandBase {
    private final Intake _intake;

    public StartIntake(Intake intake){
        _intake = intake;
    }

    @Override
    public void execute() {
        _intake.setIntakePower(0.7);
    }

    @Override
    public boolean isFinished() {
        return Components.Intake.getBottomBallSensor() && !Components.Intake.getTopBallSensor();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            _intake.setIntakePower(0.0);
        }
    }
}
