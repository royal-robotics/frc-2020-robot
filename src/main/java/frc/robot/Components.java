package frc.robot;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public final class Components
{
    public static class Drivebase
    {
        public static CANSparkMax left_motor1 = new CANSparkMax(1, MotorType.kBrushless);
        public static CANSparkMax left_motor2 = new CANSparkMax(2, MotorType.kBrushless);
        public static CANSparkMax right_motor1 = new CANSparkMax(3, MotorType.kBrushless);
        public static CANSparkMax right_motor2 = new CANSparkMax(4, MotorType.kBrushless);

        // TODO: Edit this for 2019 robot test
        //public static CANSparkMax elevator_motor = new CANSparkMax(5, MotorType.kBrushless);
    }
}
