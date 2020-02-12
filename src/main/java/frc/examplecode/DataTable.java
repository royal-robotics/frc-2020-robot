package frc.examplecode;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DataTable
{
    //All new tabs are added into the Shuffleboard table
    private static final NetworkTable table = NetworkTableInstance.getDefault().getTable("Shuffleboard");

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

    public static void Update(String shuffleboard_tab, String entry_name, Boolean data)
    {
        table.getSubTable(shuffleboard_tab).getEntry(entry_name).setBoolean(data);
    }

    public static void Update(String shuffleboard_tab, String entry_name, double data)
    {
        table.getSubTable(shuffleboard_tab).getEntry(entry_name).setDouble(data);
    }

    public static void Update(String shuffleboard_tab, String entry_name, String data)
    {
        table.getSubTable(shuffleboard_tab).getEntry(entry_name).setString(data);
    }
}
