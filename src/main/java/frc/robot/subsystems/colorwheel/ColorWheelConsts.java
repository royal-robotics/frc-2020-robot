package frc.robot.subsystems.colorwheel;

import java.util.HashMap;
import java.util.Map;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.util.*;

public final class ColorWheelConsts {
    // TODO: Adjust in competition
    public static final Color Blue = ColorMatch.makeColor(0.164, 0.430, 0.405);
    public static final Color Green = ColorMatch.makeColor(0.201, 0.572, 0.227);
    public static final Color Red = ColorMatch.makeColor(0.457, 0.381, 0.162);
    public static final Color Yellow = ColorMatch.makeColor(0.316, 0.555, 0.128);

    public static final int WheelSlices = 8;

    public static final Map<Color, Color> colorMap = new HashMap<Color, Color>() {{
        put(Blue, Green);
        put(Red, Yellow);
        put(Green, Red);
        put(Yellow, Blue);
    }};

    public static Color spinToColor(Color color) {
        return colorMap.get(color);
    }
}
