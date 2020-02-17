package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.*;

import com.revrobotics.ColorSensorV3;

public final class Components {

    public static class Drivebase {
        public final static CANSparkMax leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        public final static CANSparkMax leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        public final static CANSparkMax rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        public final static CANSparkMax rightMotor2 = new CANSparkMax(2, MotorType.kBrushless);
        public final static Gyro gyro = new ADXRS450_Gyro();
    }

    public static class Intake {
        public final static WPI_TalonSRX ballIn = new WPI_TalonSRX(11); // Grabs ball
        public final static WPI_TalonSRX conveyer = new WPI_TalonSRX(8); // Feeds ball
        public final static DigitalInput ballSensor = new DigitalInput(0);
    }

    public static class Turret {
        public final static WPI_TalonSRX platform = new WPI_TalonSRX(12); // Turns the shooter
        public final static PWM hood = new PWM(0); // Angles the shooter
        public final static CANSparkMax shooterWheel = new CANSparkMax(5, MotorType.kBrushless); // Shoots ball
    }

    public static class ColorWheel {
        public final static WPI_TalonSRX wheel = new WPI_TalonSRX(9);
        public final static ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    }

    public static class Climber {
        public final static CANSparkMax elevator1 = new CANSparkMax(6, MotorType.kBrushless);
        public final static CANSparkMax elevator2 = new CANSparkMax(7, MotorType.kBrushless);
        public final static WPI_TalonSRX balance = new WPI_TalonSRX(10);
        public final static BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();
        public final static PWM lock = new PWM(1);

    }

    public static class Camera {
        // public static VideoSource limelight = CameraServer.getInstance().getServer("limelight").getSource();
    }
}
