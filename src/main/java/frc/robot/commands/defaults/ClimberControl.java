package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.climber.*;

public class ClimberControl extends CommandBase {
    private final ClimberSubsystem _climber;
    private final JoystickButton _balanceButton;

    public ClimberControl(ClimberSubsystem climber) {
        addRequirements(climber);
        _climber = climber;
        _balanceButton = Controls.Climber.balance;

        Controls.Climber.setBottom.whenPressed(new ElevatorMoveCommand(_climber, 0.0));
        Controls.Climber.setTop.whenPressed(new ElevatorMoveCommand(_climber, 12.0));
    }

    @Override
    public void execute() {
        if (_balanceButton.get()) {
            _climber.balancer.enable();
        } else {
            _climber.balancer.disable();
        }
    }
}
