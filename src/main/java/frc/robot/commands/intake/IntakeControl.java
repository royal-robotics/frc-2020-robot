package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class IntakeControl extends CommandBase {
    private final Intake _intake;
    private final Button _runBallIntake;
    private final Button _shootBall;
    private final Button _forceIntakeIn;
    private final Button _forceIntakeOut;


    public IntakeControl(Intake intake) {
        addRequirements(intake);
        _intake = intake;

        _runBallIntake = Controls.Intake.runBallIntake;
        _shootBall = Controls.Intake.shootBall;
        _forceIntakeIn = Controls.Intake.forceIntakeIn;
        _forceIntakeOut = Controls.Intake.forceIntakeOut;
    }

	@Override
    public void execute() {
        // TODO: switch these to commands.
        if (_runBallIntake.get()) {
            _intake.intakeOn();
        } else if (_shootBall.get()) {
            _intake.setPower(1.0);
        } else if (_forceIntakeIn.get()) {
            _intake.setPower(0.8);
        } else if (_forceIntakeOut.get()) {
            _intake.setPower(-8.0);
        } else {
            _intake.setPower(0.0);
        }
    }
}
