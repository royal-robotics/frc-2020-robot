package frc.libs.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.libs.controls.Controllers.*;

import frc.robot.subsystems.*;

public class Controls
{
    private static Joystick driver = new Joystick(0);
    private static Joystick operator = new Joystick(1);

    public static class TankDrive {
        private static Axis leftThrottle = new Axis(driver, Logitech310Axis.LeftStickY, 0.1);
        private static Axis rightThrottle = new Axis(driver, Logitech310Axis.RightStickY, 0.1);

        private static double getLeftThrottleValue() { return -leftThrottle.getValue(); }
        private static double getRightThrottleValue() { return -rightThrottle.getValue(); }
        //public static TankThrottleValues getThrottleValues() { return new TankThrottleValues(getLeftThrottleValue(), getRightThrottleValue()); }
    }
}
