package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public final class Components

{
    public static DigitalInput compJumper = new DigitalInput(0);

    public static class Drivebase
    {
        // public static CANSparkMax left_motor1 = new CANSparkMax(1, MotorType.kBrushless);
        // public static CANSparkMax left_motor2 = new CANSparkMax(2, MotorType.kBrushless);
        // public static CANSparkMax right_motor1 = new CANSparkMax(3, MotorType.kBrushless);
        // public static CANSparkMax right_motor2 = new CANSparkMax(4, MotorType.kBrushless);

        // TODO: Edit this for 2019 robot test
        public static WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(7);
        public static WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(5);
        public static WPI_VictorSPX leftDrive3 = new WPI_VictorSPX(3);
        public static Encoder leftEncoder = new Encoder(10, 11);

        public static WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(8);
        public static WPI_VictorSPX rightDrive2 = new WPI_VictorSPX(6);
        public static WPI_VictorSPX rightDrive3 = new WPI_VictorSPX(4);
        public static Encoder rightEncoder = new Encoder(12, 13);
    }
}
