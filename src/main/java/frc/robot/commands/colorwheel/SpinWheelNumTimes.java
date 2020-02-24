package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.colorwheel.*;

public class SpinWheelNumTimes extends CommandBase {
    private final ColorWheel _colorwheel;
    private final int _numSpins;
    private final Timer _timeoutTimer;

    public SpinWheelNumTimes(ColorWheel colorWheel, int numSpins) {
        _colorwheel = colorWheel;
        _numSpins = numSpins;
        _timeoutTimer = new Timer();
    }

    @Override
    public void initialize() {
        _timeoutTimer.reset();
        _timeoutTimer.start();
        _colorwheel.getEncoder().reset();
        _colorwheel.setPower(1.0);
    }

    @Override
    public boolean isFinished() {
        // Turn the motor off if some idiot hit the button
        // and we're not at the color wheel.
        final var TimeoutSeconds = 15.0;
        if (_timeoutTimer.hasPeriodPassed(TimeoutSeconds)) {
            System.out.println("Warning: Sping wheel timeout");
            return true;
        }

        return _colorwheel.getEncoder().getTurns() >= _numSpins;
    }

    @Override
    public void end(boolean interrupted) {
        _timeoutTimer.stop();
        _colorwheel.setPower(0.0);
    }
}
