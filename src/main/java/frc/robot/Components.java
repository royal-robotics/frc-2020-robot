package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public final class Components

{
    public static DigitalInput compJumper = new DigitalInput(0);

    public static class Drivebase
    {
        public static CANSparkMax _leftMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        public static CANSparkMax _leftMotor2 = new CANSparkMax(2, MotorType.kBrushless);
        public static CANSparkMax _rightMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        public static CANSparkMax _rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);
    }

    // **********************************2019 robot motors*******************************
    // public static class HatchManipulator
    // {
    //     public static DoubleSolenoid carriageFingers = new DoubleSolenoid(1, 0, 7);
    //     public static DoubleSolenoid carriageArm = new DoubleSolenoid(1, 2, 5);
    // }

    // public static class OldDrivebase
    // {
    //     public static WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(7);
    //     public static WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(5);
    //     public static WPI_VictorSPX leftDrive3 = new WPI_VictorSPX(3);
    //     public static Encoder leftEncoder = new Encoder(10, 11);

    //     // TODO: Edit this for 2019 robot test
    //     public static WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(7);
    //     public static WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(5);
    //     public static WPI_VictorSPX leftDrive3 = new WPI_VictorSPX(3);
    //     public static Encoder leftEncoder = new Encoder(10, 11);

    //     public static WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(8);
    //     public static WPI_VictorSPX rightDrive2 = new WPI_VictorSPX(6);
    //     public static WPI_VictorSPX rightDrive3 = new WPI_VictorSPX(4);
    //     public static Encoder rightEncoder = new Encoder(12, 13);
    // }
}
