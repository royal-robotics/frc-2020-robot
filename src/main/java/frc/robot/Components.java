package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.*;
import frc.libs.components.Limelight;

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
        public final static DigitalInput ballSensorBottom = new DigitalInput(2);
        public final static DigitalInput ballSensorTop = new DigitalInput(3);
    }

    public static class Shooter {
        public final static WPI_TalonSRX turret = new WPI_TalonSRX(12); // Turns the shooter
        public final static Encoder turretEncoder = new Encoder(14, 15); // Enc3
        public final static DigitalInput leftLimit = new DigitalInput(1);
        public final static DigitalInput rightlimit = new DigitalInput(0);

        public final static PWM hood = new PWM(0); // Angles the shooter
        public final static Encoder hoodEncoder = new Encoder(16, 17); // Enc4

        public final static CANSparkMax shooterWheel = new CANSparkMax(5, MotorType.kBrushless);
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
        public final static Limelight limelight = new Limelight();
    }
}
