package frc.libs.controls;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.*;
import static frc.libs.controls.Controllers.*;

// TODO: We can make this a trigger if we need, or another class called AxisTrigger
public class Axis {
    private final Supplier<Double> _rawAxis;
    private final double _deadband;

    public Axis(Joystick joystick, Logitech310Axis axisIndex, double deadband) {
        _rawAxis = () -> joystick.getRawAxis(axisIndex.id);
        _deadband = deadband;
    }

    public double get() {
        final var rawAxis = _rawAxis.get();
        if (Math.abs(rawAxis) < _deadband) {
            return 0.0;
        }
        return rawAxis;
    }
}
