package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Controls;
import frc.robot.commands.*;
import frc.robot.subsystems.colorwheel.*;

public class ColorWheelDefault extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;

    public ColorWheelDefault(ColorWheelSubsystem colorWheel) {
        addRequirements(colorWheel);
        _colorWheel = colorWheel;

        colorWheel.setDefaultCommand(new ColorWheelDefault(colorWheel));
        // Controls.ColorWheel.turnWheelSetTimes.whenPressed(new SpinWheelNumTimes(_colorWheel, 4));
        // Controls.ColorWheel.turnWheelToColor.whenPressed(new SpinWheelToColor(_colorWheel, ColorWheel.Blue));
    }

	@Override
    public void execute() {
        _colorWheel.setPower(0.0);
    }
}
