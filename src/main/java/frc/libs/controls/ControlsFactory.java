package frc.libs.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;

import static frc.libs.controls.Controllers.*;

public class ControlsFactory {
    private final Joystick _driver;
    private final Joystick _operator;

    public ControlsFactory(final Joystick driver, final Joystick operator) {
        _driver = driver;
        _operator = operator;
    }

    public Axis createAxis(Controller controller, Logitech310Axis axis) {
        final double defaultDeadband = 0.05;
        final var joystick = controller == Controller.Driver ? _driver : _operator;
        return new Axis(joystick, axis, defaultDeadband);
    }

    public JoystickButton createButton(Controller controller, Logitech310Button button) {
        final var joystick = controller == Controller.Driver ? _driver : _operator;
        return new JoystickButton(joystick, button.id);
    }

    public DPadButton createDPadButton(Controller controller, DPadButton.Direction direction) {
        final var joystick = controller == Controller.Driver ? _driver : _operator;
        return new DPadButton(joystick, direction);
    }
}
