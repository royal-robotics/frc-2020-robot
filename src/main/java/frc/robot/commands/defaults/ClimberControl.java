package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.climber.*;

public class ClimberControl extends CommandBase {
    private final ClimberSubsystem _climber;

    public ClimberControl(ClimberSubsystem climber) {
        _climber = climber;

        Controls.Climber.setBottom.whenPressed(new ElevatorMoveCommand(climber, 0.0));
        Controls.Climber.setTop.whenPressed(new ElevatorMoveCommand(climber, 12.0));
    }

    @Override
    public void execute() {

    }
}
