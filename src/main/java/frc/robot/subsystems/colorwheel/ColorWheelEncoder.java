package frc.robot.subsystems.colorwheel;

import com.revrobotics.*;
import com.revrobotics.ColorSensorV3.*;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Components;

public class ColorWheelEncoder {
    private final Color _red = ColorWheelConsts.Red;
    private final Color _green = ColorWheelConsts.Green;
    private final Color _blue = ColorWheelConsts.Blue;
    private final Color _yellow = ColorWheelConsts.Yellow;

    private final ColorSensorV3 _colorSensor;
    private final ColorMatch _colorMatcher = new ColorMatch();

    private int _sliceCount = 0;
    private Color _lastColor = ColorMatch.makeColor(0.0, 0.0, 0.0);

    public ColorWheelEncoder() {
        _colorSensor = Components.ColorWheel.colorSensor;

        _colorMatcher.addColorMatch(_red);
        _colorMatcher.addColorMatch(_blue);
        _colorMatcher.addColorMatch(_green);
        _colorMatcher.addColorMatch(_yellow);
    }

    public Color getActualColor() {
        return _colorSensor.getColor();
    }

    public Color getColor() {
        return _lastColor;
    }

    public String getColorString() {
        ColorMatchResult match = _colorMatcher.matchClosestColor(getColor());
        if (match.color == _blue) {
            return "Blue";
          } else if (match.color == _red) {
            return "Red";
          } else if (match.color == _green) {
            return "Green";
          } else if (match.color == _yellow) {
            return "Yellow";
          } else {
            return "Unknown";
          }
    }

    public double getTurns() {
        return _sliceCount / ColorWheelConsts.WheelSlices;
    }

    public void reset() {
        _sliceCount = 0;
    }

    public void updateControlLoop() {
        final var matchResult = _colorMatcher.matchClosestColor(_colorSensor.getColor());
        if (matchResult.confidence < 0.9) {
            // We're not looking at the color wheel.
            return;
        }

        if (!matchResult.color.equals(_lastColor)) {
            _sliceCount++;
            _lastColor = matchResult.color;
        }
    }
}
