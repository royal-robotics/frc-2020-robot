package frc.robot;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public final class Components {
    public static class Drivebase {
        public static CANSparkMax leftMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        public static CANSparkMax leftMotor2 = new CANSparkMax(2, MotorType.kBrushless);
        public static CANSparkMax rightMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        public static CANSparkMax rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);
    }
}
