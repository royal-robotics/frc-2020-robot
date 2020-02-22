package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Components;
import frc.robot.subsystems.Intake;

public class SmartIntake extends CommandBase {
    private final Intake _intake;

    public SmartIntake(Intake intake) {
        _intake = intake;
    }

    @Override
    public void initialize() {
        _intake.setIntakePower(0.8);
    }

    @Override
    public void execute() {
        if (Components.Intake.getBottomBallSensor() && !Components.Intake.getTopBallSensor()) {
            _intake.setConveyorPower(0.8);
        } else {
            _intake.setConveyorPower(0.0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setIntakePower(0.0);
        _intake.setConveyorPower(0.0);
    }
}
