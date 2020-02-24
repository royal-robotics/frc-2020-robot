package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.robot.*;
import frc.robot.subsystems.colorwheel.*;

public class ColorWheelDefault extends CommandBase {
    private final ColorWheel _colorWheel;
    private final Axis _throttle;

    public ColorWheelDefault(ColorWheel colorWheel) {
        addRequirements(colorWheel);
        _colorWheel = colorWheel;

        _throttle = Controls.ColorWheel.throttle;
        Controls.ColorWheel.turnWheelSetTimes.whenPressed(new SpinWheelNumTimes(_colorWheel, 3));
        Controls.ColorWheel.turnWheelToColor.whenPressed(new SpinWheelToColor(_colorWheel, ColorWheelConsts.spinToColor(ColorWheelConsts.Blue)));
    }

	@Override
    public void execute() {
        final var power = _throttle.get();
        _colorWheel.setPower(power);
    }
}
