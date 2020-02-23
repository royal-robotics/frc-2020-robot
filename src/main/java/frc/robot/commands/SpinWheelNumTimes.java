package frc.robot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.colorwheel.*;

public class SpinWheelNumTimes extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;
    private final int _numSpins;
    private final Timer _timeoutTimer;

    public SpinWheelNumTimes(ColorWheelSubsystem colorWheel, int numSpins) {
        _colorWheel = colorWheel;
        _numSpins = numSpins;
        _timeoutTimer = new Timer();
    }

    @Override
    public void initialize() {
        _timeoutTimer.reset();
        _timeoutTimer.start();
        _colorWheel.getEncoder().reset();
        _colorWheel.setPower(1.0);
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

        return _colorWheel.getEncoder().getTurns() >= _numSpins;
    }

    @Override
    public void end(boolean interrupted) {
        _timeoutTimer.stop();
        _colorWheel.setPower(0.0);
    }
}
