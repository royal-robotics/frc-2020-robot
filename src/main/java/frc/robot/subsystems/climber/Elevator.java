package frc.robot.subsystems.climber;

import com.revrobotics.*;
import frc.libs.components.*;
import frc.robot.*;

public class Elevator {
    private final CANSparkMax _elevator;
    private final EncoderGroup _encoder;
    private final ElevatorPidController _elevatorPID;

    public Elevator() {
        _elevator = Components.Climber.elevator1;
        final var elevator2 = Components.Climber.elevator2;
        elevator2.follow(_elevator);

        final var inchesPerTurn = 0.0; // TODO: calculate this
        _encoder = new EncoderGroup(inchesPerTurn, false, _elevator.getEncoder(), elevator2.getEncoder());

        _elevatorPID = new ElevatorPidController();
        _elevatorPID.setTolerance(1.0);
    }

    /// <remarks>
    /// PidController assumes this is called every 20ms.
    /// Implement a notifer loop here instead if this changes
    /// or isn't convenient for the caller.
    /// </remarks>
    public void updateControlLoop() {
        final var heightInches = _encoder.getPosition();
        _elevator.set(_elevatorPID.calculate(heightInches));
    }

    public void setGoalHeight(double heightInches) {
        setGoalHeight(heightInches, false);
    }

    public void setGoalHeight(double heightInches, boolean reset) {
        _elevatorPID.setSetpoint(heightInches);

        if (reset) {
            _elevatorPID.reset();
        }
    }

    public boolean atGoalHeight() {
        return _elevatorPID.atSetpoint();
    }
}
