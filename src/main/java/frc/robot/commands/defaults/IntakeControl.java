package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.libs.controls.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class IntakeControl extends CommandBase {
    private final IntakeSubsystem _intake;
    private final Button _runBallIntake;
    private final Button _runConveyorUp;
    private final Button _shootBall;

    public IntakeControl(IntakeSubsystem intake) {
        addRequirements(intake);
        _intake = intake;

        _runBallIntake = Controls.Intake.runBallIntake;
        _runConveyorUp = Controls.Intake.runConveyorUp;
        _shootBall = Controls.Intake.shootBall;
    }

	@Override
    public void execute() {
        if (_runBallIntake.get()) {
            _intake.setIntakePower(0.7);
        } else {
            _intake.setIntakePower(0.0);
        }

        if (_shootBall.get()) {
            _intake.setConveyerPower(-1.0);
        } else if(_runConveyorUp.get()) {
            _intake.setConveyerPower(-0.7);
        } else {
            _intake.setConveyerPower(0.0);
        }
    }
}
