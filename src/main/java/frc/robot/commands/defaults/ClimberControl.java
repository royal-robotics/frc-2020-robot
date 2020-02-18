package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.libs.controls.Axis;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.climber.*;

public class ClimberControl extends CommandBase {
    private final ClimberSubsystem _climber;
    private final Button _autoBalanceButton;
    private final Axis _moveElevator;

    public ClimberControl(ClimberSubsystem climber) {
        addRequirements(climber);
        _climber = climber;
        _autoBalanceButton = Controls.Climber.autoBalance;
        _moveElevator = Controls.Climber.moveElevator;

        Controls.Climber.quickMoveElevatorBottom.whenPressed(new ElevatorMoveCommand(_climber, 0.0));
        Controls.Climber.quickMoveElevatorTop.whenPressed(new ElevatorMoveCommand(_climber, 12.0));
    }

    @Override
    public void execute() {
        if (_autoBalanceButton.get()) {
            _climber.balancer.enable();
        } else {
            _climber.balancer.disable();
        }

        // TODO: This needs position holder logic when the axis is at the deadband
        // final var elevatorPower = _moveElevator.get();
        // _climber.
    }
}
