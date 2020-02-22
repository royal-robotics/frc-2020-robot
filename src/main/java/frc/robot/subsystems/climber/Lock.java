package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;

public class Lock extends SubsystemBase {
    private final PWM _lock;

    public Lock() {
        _lock = Components.Climber.lock;

        set(false);
    }

    public void set(boolean lockOn) {
        _lock.setPosition(lockOn ? 0.0 : 1.0);
    }

    public boolean get() {
        return _lock.getPosition() == 0.0;
    }

    public void toggle() {
        System.out.println("Toggle! " + _lock.getPosition());
        set(!get());
    }
}
