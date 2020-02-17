package frc.robot.subsystems.colorwheel;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;

import edu.wpi.first.wpilibj.util.Color;
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
    }
}
