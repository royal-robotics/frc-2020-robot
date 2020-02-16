package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.libs.components.*;
import frc.robot.*;
import frc.robot.subsystems.*;

public class ClimberSubsystem extends RoyalSubsystem {
    private final CANSparkMax _elevator;
    private final EncoderGroup _elevatorEncoder;
    private final WPI_TalonSRX _balance;

    private final ElevatorPidController _elevatorPID;

    public ClimberSubsystem() {
        _elevator = Components.Climber.elevator1;
        final var elevator2 = Components.Climber.elevator2;
        elevator2.follow(_elevator);

        final var inchesPerTurn = 0.0; // TODO: calculate this
        _elevatorEncoder = new EncoderGroup(inchesPerTurn, false, _elevator.getEncoder(), elevator2.getEncoder());

        _balance = Components.Climber.balance;

        _elevatorPID = new ElevatorPidController();
    }

    public void setHeight(double heightInches) {
        _elevatorPID.setSetpoint(heightInches);
    }

    public double getHeight() {
        return _elevatorEncoder.getPosition();
    }

    public void reinitializeHeight(double heightInches) {
        _elevatorPID.reset();
        _elevatorPID.setSetpoint(heightInches);
    }

    @Override
    public void periodic() {
        _elevator.set(_elevatorPID.calculate(getHeight()));
    }
}
