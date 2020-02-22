package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class IntakeDefault extends CommandBase {
    private final Intake _intake;
    // private final Button _runBallIntake;
    private final Button _shootBall;
    private final Button _forceIntakeIn;
    private final Button _forceIntakeOut;


    public IntakeDefault(Intake intake) {
        addRequirements(intake);
        _intake = intake;

        Controls.Intake.runBallIntake.whenHeld(new PickUpBall(_intake));
        // Controls.Intake.shootBall.whenHeld(new ShootBallConveyor(_intake));

        // _runBallIntake = Controls.Intake.runBallIntake;
        _shootBall = Controls.Intake.shootBall;
        _forceIntakeIn = Controls.Intake.forceIntakeIn;
        _forceIntakeOut = Controls.Intake.forceIntakeOut;

        Controls.Intake.runBallIntake.whenHeld(new SmartIntake(_intake));
    }

	@Override
    public void execute() {
        // TODO: switch these to commands.
        // if (_runBallIntake.get()) {
        //     _intake.intakeOn();
        // } else if (_shootBall.get()) {
        //     _intake.setConveyorPower(1.0);
        // } else if (_forceIntakeIn.get()) {
        //     _intake.setIntakePower(1.0);
        //     _intake.setConveyorPower(1.0);
        // } else if (_forceIntakeOut.get()) {
        //     _intake.setIntakePower(-0.8);
        //     _intake.setConveyorPower(-0.8);
        // } else {
        //     _intake.setIntakePower(0.0);
        //     _intake.setConveyorPower(0.0);
        // }

        if (_shootBall.get()) {
            _intake.setConveyorPower(1.0);
        }
        else {
            _intake.setConveyorPower(0.0);
        }
    }
}
