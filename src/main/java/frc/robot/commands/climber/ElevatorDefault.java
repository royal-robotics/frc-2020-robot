package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.Axis;
import frc.robot.*;
import frc.robot.subsystems.climber.*;

public class ElevatorDefault extends CommandBase {
    private final Elevator _elevator;
    private final Axis _moveElevator;

    public ElevatorDefault(Elevator elevator) {
        addRequirements(elevator);

        _elevator = elevator;
        _moveElevator = Controls.Climber.moveElevator;
    }

    @Override
    public void execute() {
        if (!_moveElevator.inDeadband()) {
            final var elevatorPower = -_moveElevator.get();
            _elevator.setPower(elevatorPower);
        } else if (!_elevator.isEnabled()) {
            // If the joystick isn't being used and there isn't a setpoint
            // Then we make the setpoint be the current position.
            _elevator.setHeight(_elevator.getHeight());
            _elevator.enable();
        }
    }
}
