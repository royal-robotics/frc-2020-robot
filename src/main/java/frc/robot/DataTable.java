package frc.robot;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class DataTable
{
    public static void MakeBooleanEntry(String shuffleboard_tab, String entry_name)
    {
        Shuffleboard.getTab(shuffleboard_tab).add(entry_name, false).getEntry();
    }

    public static void MakeDoubleEntry(String shuffleboard_tab, String entry_name)
    {
        Shuffleboard.getTab(shuffleboard_tab).add(entry_name, 0).getEntry();
    }

    public static void MakeStringEntry(String shuffleboard_tab, String entry_name)
    {
        Shuffleboard.getTab(shuffleboard_tab).add(entry_name, "").getEntry();
    }

    public static void Update(String entry_name, Boolean data)
    {
        NetworkTableInstance.getDefault().getEntry(entry_name).setBoolean(data);
    }

    public static void Update(String entry_name, double data)
    {
        NetworkTableInstance.getDefault().getEntry(entry_name).setDouble(data);
    }

    public static void Update(String entry_name, String data)
    {
        NetworkTableInstance.getDefault().getEntry(entry_name).setString(data);
    }
}
