package frc.robot.commands.manual;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.libs.controls.Controllers.*;
import frc.robot.subsystems.*;

public class IntakeControl extends CommandBase {
    private final IntakeSubsystem _intake;
    private final Axis _ballInThrottle;
    private final Axis _upstreamThrottle;

    public IntakeControl(IntakeSubsystem intake, ControlsFactory controlsFactory) {
        _intake = intake;
        addRequirements(_intake);

        _ballInThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.LeftStickY);
        _upstreamThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightStickY);
    }

	@Override
    public void execute() {
        _intake.setIntakePower(_ballInThrottle.get());
        _intake.setUpstreamPower(_upstreamThrottle.get());
    }
}
