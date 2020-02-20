package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.climber.*;

public class ElevatorMoveCommand extends CommandBase {
    private final Elevator _elevator;
    private final double _heightInches;

    public ElevatorMoveCommand(Elevator elevator, double heightInches) {
        addRequirements(elevator);
        _elevator = elevator;
        _heightInches = heightInches;
    }

    @Override
    public void initialize() {
        _elevator.setHeight(_heightInches);
    }

    @Override
    public boolean isFinished() {
        return _elevator.isAtSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        _elevator.stop();
    }
}
