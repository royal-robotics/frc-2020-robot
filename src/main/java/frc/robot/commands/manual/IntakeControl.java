package frc.robot.commands.manual;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class IntakeControl extends CommandBase {
    private final IntakeSubsystem _intake;
    private final Axis _ballInThrottle;
    private final Axis _conveyerThrottle;

    public IntakeControl(IntakeSubsystem intake) {
        _intake = intake;
        addRequirements(_intake);

        _ballInThrottle = Controls.Intake.ballInThrottle;
        _conveyerThrottle = Controls.Intake.conveyerThrottle;
    }

	@Override
    public void execute() {
        _intake.setIntakePower(_ballInThrottle.get());
        _intake.setConveyerPower(_conveyerThrottle.get());
    }
}
