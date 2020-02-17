package frc.robot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.util.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.colorwheel.*;

public class SpinWheelToColor extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;
    private final Color _color;
    private final Timer _timeoutTimer;

    public SpinWheelToColor(ColorWheelSubsystem colorWheel, Color color) {
        addRequirements(colorWheel);
        _colorWheel = colorWheel;
        _color = color;
        _timeoutTimer = new Timer();
    }

    @Override
    public void initialize() {
        _timeoutTimer.reset();
        _timeoutTimer.start();
        _colorWheel.setPower(0.8);
    }

    @Override
    public boolean isFinished() {
        // Turn the motor off if some idiot hit the button
        // and we're not at the color wheel.
        final var TimeoutSeconds = 5.0;
        if (_timeoutTimer.hasPeriodPassed(TimeoutSeconds)) {
            System.out.println("Warning: Sping wheel timeout");
            return true;
        }

        return _colorWheel.getEncoder().getColor().equals(_color);
    }


    @Override
    public void end(boolean interrupted) {
        _timeoutTimer.stop();
        _colorWheel.setPower(0.0);
    }
}
