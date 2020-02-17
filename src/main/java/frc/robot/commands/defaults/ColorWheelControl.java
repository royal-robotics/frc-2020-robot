package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class ColorWheelControl extends CommandBase {
    private final ColorWheelSubsystem _colorWheel;

    public ColorWheelControl(ColorWheelSubsystem colorWheel) {
        _colorWheel = colorWheel;
        addRequirements(_colorWheel);
    }

	@Override
    public void execute() {
        final var turnWheelButton = Controls.ColorWheel.turnWheel;
        final var wheelPower = turnWheelButton.get() ? 0.7 : 0.0;
        _colorWheel.setPower(wheelPower);
    }
}
