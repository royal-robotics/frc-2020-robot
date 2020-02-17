package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Controls;
import frc.robot.commands.*;
import frc.robot.subsystems.colorwheel.*;

public class ColorWheelControl extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;

    public ColorWheelControl(ColorWheelSubsystem colorWheel) {
        addRequirements(colorWheel);
        _colorWheel = colorWheel;

        Controls.ColorWheel.turnWheelSetTimes.whenPressed(new SpinWheelNumTimes(_colorWheel, 4));

        //TODO: allow setting of different colors
        Controls.ColorWheel.turnWheelToColor.whenPressed(new SpinWheelToColor(_colorWheel, ColorWheel.Green));
    }

	@Override
    public void execute() {
    }
}
