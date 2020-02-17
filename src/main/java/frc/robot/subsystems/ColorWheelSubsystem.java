package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.*;

public class ColorWheelSubsystem extends RoyalSubsystem {
    private final WPI_TalonSRX _wheel;

    public ColorWheelSubsystem() {
        _wheel = Components.ColorWheel.wheel;
    }

    public void setPower(double power) {
        _wheel.set(power);
    }

    @Override
    public void periodic() {

    }
}
