package frc.examplecode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.*;

public class OldDrivebaseSubsystem //extends RoyalSubsystem
{
    public final SpeedController leftDrive;
    public final SpeedController rightDrive;

    public OldDrivebaseSubsystem()
    {
        final WPI_TalonSRX leftDrive1 = Components.OldDrivebase.leftDrive1;
        Components.OldDrivebase.leftDrive2.follow(leftDrive1);
        Components.OldDrivebase.leftDrive3.follow(leftDrive1);
        leftDrive = leftDrive1;

        leftDrive1.configPeakCurrentLimit(10);

        final WPI_TalonSRX rightDrive1 = Components.OldDrivebase.rightDrive1;
        rightDrive1.setInverted(true);
        Components.OldDrivebase.rightDrive2.setInverted(true);
        Components.OldDrivebase.rightDrive3.setInverted(true);
        Components.OldDrivebase.rightDrive2.follow(rightDrive1);
        Components.OldDrivebase.rightDrive3.follow(rightDrive1);
        rightDrive = rightDrive1;
    }

    public void setPower(double left_power, double right_power)
    {
        leftDrive.set(left_power);
        rightDrive.set(right_power);
    }
}
