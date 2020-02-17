package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.libs.controls.Axis;
import frc.libs.controls.ControlsFactory;
import frc.libs.controls.Controllers.Controller;
import frc.libs.controls.Controllers.Logitech310Axis;
import frc.libs.controls.Controllers.Logitech310Button;

public final class Controls {
    private final static Joystick driver = new Joystick(0);
    private final static Joystick operator = new Joystick(1);
    private final static ControlsFactory controlsFactory = new ControlsFactory(driver, operator);

    public static class DifferentialDrive {
        public final static Axis throttle = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
        public final static Axis steer = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickX);
    }

    public static class TankDrive {
        public final static Axis leftAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
        public final static Axis rightAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickY);
    }

    public static class Intake {
        public final static Axis ballInThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.LeftStickY);
        public final static Axis conveyerThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightStickY);
    }

    public static class Turret {
        public final static Axis platformTurner = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.LeftStickX);
        // public final static Axis arcAngler = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightStickY);
        public final static Axis wheelThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightTrigger);
    }

    public static class ColorWheel {

    }

    public static class Climber {
        public final static JoystickButton setBottom = controlsFactory.createButton(Controller.Operator, Logitech310Button.A);
        public final static JoystickButton setTop = controlsFactory.createButton(Controller.Operator, Logitech310Button.Y);
    }
}
