package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;

public final class Components {
    public static class Drivebase {
        public static CANSparkMax leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        public static CANSparkMax leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        public static CANSparkMax rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        public static CANSparkMax rightMotor2 = new CANSparkMax(2, MotorType.kBrushless);
    }

    public static class Intake {
        public static WPI_TalonSRX ballIn = new WPI_TalonSRX(7);
        public static WPI_TalonSRX upstream = new WPI_TalonSRX(7);
    }

    public static class Camera {
        // public static VideoSource limelight = CameraServer.getInstance().getServer("limelight").getSource();
    }
}
