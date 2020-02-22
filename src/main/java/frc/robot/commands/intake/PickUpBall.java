package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class PickUpBall extends SequentialCommandGroup {
    private final Intake _intake;

    public PickUpBall(Intake intake) {
        _intake = intake;
        this.addCommands(new StartIntake(intake), new StartConveyor(intake), new WaitCommand(0.1), new StopAllIntake(intake));
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        if (interrupted) {
            _intake.setIntakePower(0.0);
            _intake.setConveyorPower(0.0);
        }
    }
}
