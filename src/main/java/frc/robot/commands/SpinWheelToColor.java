package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.colorwheel.*;

public class SpinWheelToColor extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;
    private final Color _color;

    public SpinWheelToColor(ColorWheelSubsystem colorWheel, Color color) {
        addRequirements(colorWheel);
        _colorWheel = colorWheel;
        _color = color;
    }

    @Override
    public void initialize() {
        _colorWheel.setPower(0.8);
    }

    @Override
    public boolean isFinished() {
        return _colorWheel.getEncoder().getColor().equals(_color);
    }


    @Override
    public void end(boolean interrupted) {
        _colorWheel.setPower(0.0);
    }
}
