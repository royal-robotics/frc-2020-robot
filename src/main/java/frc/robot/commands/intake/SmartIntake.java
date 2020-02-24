package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.*;

public class SmartIntake extends CommandBase {
    private final Intake _intake;

    private boolean _extraConveyor = false;
    private int _extraFrames = 0;

    public SmartIntake(Intake intake) {
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
                _intake.setConveyorPower(0.8);
                _extraConveyor = true;
            } else if (_extraConveyor && (_extraFrames <= 3)) {
                _extraFrames++;
            } else {
                _intake.setConveyorPower(0.0);
                _extraFrames = 0;
                _extraConveyor = false;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setIntakePower(0.0);
        _intake.setConveyorPower(0.0);
    }
}
