package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.climber.*;

public class ElevatorMoveCommand extends CommandBase {
    private final ClimberSubsystem _climber;
    private final double _heightInches;

    public ElevatorMoveCommand(ClimberSubsystem climber, double heightInches) {
        _climber = climber;
        _heightInches = heightInches;
    }

    @Override
    public void initialize() {
        _climber.setGoalHeight(_heightInches);
    }

    @Override
    public boolean isFinished() {
        return _climber.atGoalHeight();
    }
}
