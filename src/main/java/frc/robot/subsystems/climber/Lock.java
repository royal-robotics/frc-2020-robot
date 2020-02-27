package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;

public class Lock extends SubsystemBase {
    private final PWM _lock;
    private final LockValidator _lockValidator;

    public Lock() {
        _lock = Components.Climber.lock;
        _lockValidator = new LockValidator(this);

        set(false);
    }

    public void set(boolean lockOn) {
        final var oldValue = get();
        _lock.setPosition(lockOn ? 0.0 : 1.0);
        final var newValue = get();

        if (!oldValue && newValue) {
            _lockValidator.start();
        }
    }

    public boolean get() {
        return _lock.getPosition() == 0.0;
    }

    public void toggle() {
        set(!get());
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Climber/Lock/Enabled", get());
        SmartDashboard.putBoolean("Climber/Lock/Confirmed", _lockValidator.isValidated());
    }

    private class LockValidator {
        private final Lock _lock;
        private final Notifier _loop = new Notifier(() -> checkLock());
        private Double _lockAttemptStart = null;
        private Double _lockAttemptStartPosition = null;
        private boolean _isValidated = false;

        public LockValidator(Lock lock) {
            _lock = lock;
        }

        public void start() {
            _loop.startPeriodic(0.01);
        }

        private void checkLock() {
            if (!_lock.get()) {
                _isValidated = false;
                _loop.stop();
            }

            final var MovementCheckIntervalMs = 250.0;
            final var MovementCheckMaxChange = 0.5;
            if (isElevatorTryingToMove()) {
                if (_lockAttemptStart == null) {
                    _lockAttemptStart = getMsClock();
                    _lockAttemptStartPosition = getElevatorPosition();
                } else if (getMsClock() > _lockAttemptStart + MovementCheckIntervalMs) {
                    if (Math.abs(getElevatorPosition() - _lockAttemptStartPosition) < MovementCheckMaxChange) {
                        _isValidated = true;
                    } else {
                        _lockAttemptStart = null;
                    }
                }
            } else {
                _lockAttemptStart = null;
            }
        }

        public boolean isValidating() {
            throw new RuntimeException("TODO: Implement");
        }

        public boolean isValidated() {
            return _isValidated;
        }

        private boolean isElevatorTryingToMove() {
            final var elevator1Amps = Components.Climber.elevator1.getOutputCurrent();
            final var elevator2Amps = Components.Climber.elevator2.getOutputCurrent();
            final var elevatorAmps = (elevator1Amps + elevator2Amps) / 2.0;
            return elevatorAmps > 1.5;
        }

        private double getElevatorPosition() {
            final var elevator1Pos = Components.Climber.elevator1.getEncoder().getPosition();
            final var elevator2Pos = Components.Climber.elevator2.getEncoder().getPosition();
            return (elevator1Pos + elevator2Pos) / 2.0;
        }

        private double getMsClock() {
            return RobotController.getFPGATime() / 1000.0;
        }
    }
}
