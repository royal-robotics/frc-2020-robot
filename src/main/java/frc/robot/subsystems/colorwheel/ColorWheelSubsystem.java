package frc.robot.subsystems.colorwheel;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.*;
import frc.robot.subsystems.*;

public class ColorWheelSubsystem extends RoyalSubsystem {
    private final ColorWheelEncoder _encoder;
    private final WPI_TalonSRX _wheel;

    public ColorWheelSubsystem() {
        _encoder = new ColorWheelEncoder();
        _wheel = Components.ColorWheel.wheel;
    }

    public void setPower(double power) {
        _wheel.set(power);
    }

    public ColorWheelEncoder getEncoder() {
        return _encoder;
    }

    @Override
    public void periodic() {
        _encoder.updateControlLoop();
        SmartDashboard.putNumber("ColorWheel/Sensor/Tick", _encoder.getTurns());
        // SmartDashboard.putNumber("ColorWheel/Sensor/Red", _encoder.getActualColor().red);
        // SmartDashboard.putNumber("ColorWheel/Sensor/Green", _encoder.getActualColor().green);
        // SmartDashboard.putNumber("ColorWheel/Sensor/Blue", _encoder.getActualColor().blue);
        SmartDashboard.putString("ColorWheel/Sensor/ColorSensor", _encoder.colorSensorColor());
        SmartDashboard.putString("ColorWheel/Sensor/FieldSensor", _encoder.fieldSensorColor());
        SmartDashboard.putNumber("ColorWheel/Motor/Power", _wheel.get());
    }
}
