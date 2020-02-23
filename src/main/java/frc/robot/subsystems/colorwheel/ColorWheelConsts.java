package frc.robot.subsystems.colorwheel;

import java.util.HashMap;
import java.util.Map;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.util.*;

public final class ColorWheelConsts {
    // TODO: Adjust in competition
    public static final Color Blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color Green = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color Red = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color Yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public static final int WheelSlices = 8;

    public static final Map<Color, Color> colorMap = new HashMap<Color, Color>() {{
        put(Blue, Red);
        put(Red, Blue);
        put(Green, Yellow);
        put(Yellow, Green);
    }};

    public static Color spinToColor(Color color) {
        return colorMap.get(color);
    }
}
