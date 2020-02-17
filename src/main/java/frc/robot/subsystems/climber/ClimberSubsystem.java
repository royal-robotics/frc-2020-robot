package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PWM;
import frc.robot.*;
import frc.robot.subsystems.*;

public class ClimberSubsystem extends RoyalSubsystem {
    public final Elevator elevator;
    private final WPI_TalonSRX _balance;
    private final PWM _lock;

    public ClimberSubsystem() {
        elevator = new Elevator();
        _balance = Components.Climber.balance;
        _lock = Components.Climber.lock;
    }

    public void setLock(boolean isLocked) {
        // TODO: figure out servo positions
        final var openPosition = 200.0;
        final var lockPosition = 150.0;
        _lock.setPosition(isLocked ? lockPosition : openPosition);
    }

    @Override
    public void periodic() {
        elevator.updateControlLoop();
    }
}
