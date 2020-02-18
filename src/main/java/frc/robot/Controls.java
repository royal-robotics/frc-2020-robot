package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.libs.controls.*;
import frc.libs.controls.Controllers.*;
import frc.libs.controls.DPadButton.Direction;
import frc.libs.models.*;
import frc.robot.shuffleboard.*;

public final class Controls {
    private final static Joystick driver = new Joystick(0);
    private final static Joystick operator = new Joystick(1);
    private final static ControlsFactory controlsFactory = new ControlsFactory(driver, operator);

    public static class Intake {
        public final static Button runBallIntake = controlsFactory.createButton(Controller.Operator, Logitech310Button.B);
        public final static Button shootBall = controlsFactory.createButton(Controller.Operator, Logitech310Button.RightBumper);

        // Normally this is controller by a sensor.
        public final static Button runConveyorUp = controlsFactory.createDPadButton(Controller.Operator, Direction.Up);
    }

    public static class Turret {
        // Manual controls, hopefully not needed much.
        public final static Axis moveTurret = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.LeftStickX);
        public final static Axis moveHood = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.LeftStickY);
        public final static Axis wheelThrottle = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightTrigger);

        // Currently moves the platform to the center position.
        public final static Button autoTrackShooter = controlsFactory.createButton(Controller.Operator, Logitech310Button.A);
    }

    public static class ColorWheel {
        // We'll make these controls something else later, leaving it for now because it'll be funny.
        public final static Button turnWheelSetTimes = controlsFactory.createButton(Controller.Operator, Logitech310Button.LeftStickPress);
        public final static Button turnWheelToColor = controlsFactory.createButton(Controller.Operator, Logitech310Button.RightStickPress);
    }

    public static class Climber {
        public final static Axis moveElevator = controlsFactory.createAxis(Controller.Operator, Logitech310Axis.RightStickY);
        public final static Button quickMoveElevatorTop = controlsFactory.createDPadButton(Controller.Operator, Direction.Left);
        public final static Button quickMoveElevatorBottom = controlsFactory.createDPadButton(Controller.Operator, Direction.Right);

        public final static Button autoBalance = controlsFactory.createButton(Controller.Operator, Logitech310Button.LeftBumper);
    }

    public static class DriveBase {
        public static TankThrottleValues getThrottleValues() {
            switch (Configs.getDriveControlType()) {
                case "TankDrive":
                    return TankDrive.getThrottleValues();
                case "DifferentialDrive":
                    return DifferentialDrive.getThrottleValues();
                // case "CheesyDrive":
                //     return Controls.DriveSystem.CheesyDrive.getThrottleValues();
                default:
                    throw new UnsupportedOperationException();
            }
        }

        private static class DifferentialDrive {
            private final static Axis throttle = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
            private final static Axis steer = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickX);

            private static double getSteerDampened() { return steer.get() * 0.75; }
            public static TankThrottleValues getThrottleValues() { return new TankThrottleValues(throttle.get() + getSteerDampened(), throttle.get() - getSteerDampened()); }
        }

        private static class TankDrive {
            private final static Axis leftAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
            private final static Axis rightAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickY);

            public static TankThrottleValues getThrottleValues() { return new TankThrottleValues(leftAxis.get(), rightAxis.get()); }
        }
    }
}
