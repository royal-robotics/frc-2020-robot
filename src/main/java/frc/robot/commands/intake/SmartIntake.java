package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class SmartIntake extends CommandBase {
    private final Intake _intake;

    public SmartIntake(Intake intake) {
        addRequirements(intake);
        _intake = intake;
    }

    @Override
    public void execute() {

        if (_intake.isBallAtTop()) {
            // We can't intake more balls if there's one at the top already.
            _intake.setConveyorPower(0.0);
            _intake.setIntakePower(0.0);
        } else {
            _intake.setIntakePower(0.8);

            // Only run the intake if there's a ball visible.
            if (_intake.isBallAtBottom()) {
                _intake.setConveyorPower(1.0);
            } else {
                _intake.setConveyorPower(0.0);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setIntakePower(0.0);
        _intake.setConveyorPower(0.0);
    }
}
