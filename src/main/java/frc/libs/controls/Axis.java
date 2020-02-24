package frc.libs.controls;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.*;
import static frc.libs.controls.Controllers.*;

// Note: We can make this a trigger if we need, or another class called AxisTrigger
public class Axis {
    private final Supplier<Double> _rawAxis;
    private final double _deadband;

    public Axis(Joystick joystick, Logitech310Axis axisIndex, double deadband) {
        _rawAxis = () -> joystick.getRawAxis(axisIndex.id);
        _deadband = deadband;
    }

    public double get() {
        return this.get(false);
    }

    public double get(boolean invert) {
        if (inDeadband()) {
            return 0.0;
        }
        final var rawAxis = _rawAxis.get();
        return invert ? -rawAxis : rawAxis;
    }

    public double getSquared() {
        if (inDeadband()) {
            return 0.0;
        }

        final var rawAxis = _rawAxis.get();
        if (rawAxis < 0.0) {
            return -(rawAxis * rawAxis);
        } else {
            return rawAxis * rawAxis;
        }
    }

    public boolean inDeadband() {
        final var rawAxis = _rawAxis.get();
        return Math.abs(rawAxis) < _deadband;
    }
}
