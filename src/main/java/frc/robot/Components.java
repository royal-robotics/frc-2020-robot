package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PWM;

public final class Components {
    public static class Drivebase {
        public final static CANSparkMax leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        public final static CANSparkMax leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        public final static CANSparkMax rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        public final static CANSparkMax rightMotor2 = new CANSparkMax(2, MotorType.kBrushless);
    }

    public static class Intake {
        public final static WPI_TalonSRX ballIn = new WPI_TalonSRX(11); // Grabs ball
        public final static WPI_TalonSRX conveyer = new WPI_TalonSRX(7); // Feeds ball
    }

    public static class Turret {
        public final static WPI_TalonSRX platform = new WPI_TalonSRX(12); // Turns the shooter
        // public static PWM arc = new PWM(); // Angles the shooter
        public final static CANSparkMax shooterWheel = new CANSparkMax(5, MotorType.kBrushless); // Shoots ball
    }

    public static class ColorWheel {

    }

    public static class Camera {
        // public static VideoSource limelight = CameraServer.getInstance().getServer("limelight").getSource();
    }
}
