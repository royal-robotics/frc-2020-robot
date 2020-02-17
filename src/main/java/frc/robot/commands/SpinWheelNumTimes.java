package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.colorwheel.*;

public class SpinWheelNumTimes extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;
    private final int _numSpins;

    public SpinWheelNumTimes(ColorWheelSubsystem colorWheel, int numSpins) {
        _colorWheel = colorWheel;
        _numSpins = numSpins;
    }

    @Override
    public void initialize() {
        _colorWheel.getEncoder().reset();
        _colorWheel.setPower(1.0);
    }

    @Override
    public boolean isFinished() {
        return _colorWheel.getEncoder().getTurns() >= _numSpins;
    }

    @Override
    public void end(boolean interrupted) {
        _colorWheel.setPower(0.0);
    }
}
