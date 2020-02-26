package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;

public class Lock extends SubsystemBase {
    private final PWM _lock;

    // private boolean _confirmationTimer
    private boolean _isConfirmed;

    public Lock() {
        _lock = Components.Climber.lock;

        set(false);
        // _isSet = false;
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

    @Override
    public void periodic() {
        if (!get()) {
            // If the lock is ever disabled, we need to reconfirm it's enabled
            _isConfirmed = false;
        } else {
            // if (Comp)

        }


        SmartDashboard.putBoolean("Climber/Lock/Enabled", get());
        SmartDashboard.putBoolean("Climber/Lock/Confirmed", _isConfirmed);
    }
}
