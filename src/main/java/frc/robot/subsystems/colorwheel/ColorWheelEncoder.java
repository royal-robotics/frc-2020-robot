package frc.robot.subsystems.colorwheel;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Components;

public class ColorWheelEncoder {

    private final ColorSensorV3 _colorSensor;
    private final ColorMatch _colorMatcher = new ColorMatch();

    private int _sliceCount = 0;
    private Color _lastColor = null;

    public ColorWheelEncoder() {
        _colorSensor = Components.ColorWheel.colorSensor;

        _colorMatcher.addColorMatch(ColorWheelConsts.Blue);
        _colorMatcher.addColorMatch(ColorWheelConsts.Green);
        _colorMatcher.addColorMatch(ColorWheelConsts.Red);
        _colorMatcher.addColorMatch(ColorWheelConsts.Yellow);
    }

    public Color getColor() {
        return _lastColor;
    }

    public double getTurns() {
        return _sliceCount / ColorWheelConsts.WheelSlices;
    }

    public void reset() {
        _sliceCount = 0;
    }

    public void updateControlLoop() {
        final var matchResult = _colorMatcher.matchClosestColor(_colorSensor.getColor());
        if (matchResult.confidence < 0.95) {
            // We're not looking at the color wheel.
            return;
        }

        if (!matchResult.color.equals(_lastColor)) {
            _sliceCount++;
            _lastColor = matchResult.color;
        }
    }
}
