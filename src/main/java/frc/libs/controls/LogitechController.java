package frc.libs.controls;

import edu.wpi.first.wpilibj.Joystick;

// I like some of the ideas about abstraction in this class, going to leave it as reference.
// But there's some built in Joystick abstractions in the WPILib command library I want us
// to try and use.
public class LogitechController {

    private final Joystick _joystick;
    private final double _deadband;

    public LogitechController(Joystick controller, double deadband) {
        _joystick = controller;
        _deadband = deadband;
    }

    public LogitechController(int port, double deadband) {
        this(new Joystick(port), deadband);
    }

    public double lXAxis() {
        double value = _joystick.getRawAxis(0);

        if (value > _deadband || value < -_deadband) {
            return value;
        }

        return 0;
    }

    public double lYAxis() {
        double value = _joystick.getRawAxis(1);

        if (value > _deadband || value < -_deadband) {
            return value;
        }

        return 0;
    }

    public double lTrigger() {
        double value = _joystick.getRawAxis(2);

        if (value > _deadband) {
            return value;
        }

        return 0;
    }

    public double rTrigger() {
        double value = _joystick.getRawAxis(3);

        if (value > _deadband) {
            return value;
        }

        return 0;
    }

    public double rXAxis() {
        double value = _joystick.getRawAxis(4);

        if (value > _deadband) {
            return value;
        }

        return 0;
    }

    public double rYAxis() {
        double value = _joystick.getRawAxis(5);

        if (value > _deadband) {
            return value;
        }

        return 0;
    }

    public boolean buttonA() {
        return _joystick.getRawButton(1);
    }

    public boolean buttonB() {
        return _joystick.getRawButton(2);
    }

    public boolean buttonX() {
        return _joystick.getRawButton(3);
    }

    public boolean buttonY() {
        return _joystick.getRawButton(4);
    }

    public boolean lBumper() {
        return _joystick.getRawButton(5);
    }

    public boolean rBumper() {
        return _joystick.getRawButton(6);
    }

    public boolean back() {
        return _joystick.getRawButton(7);
    }

    public boolean start() {
        return _joystick.getRawButton(8);
    }

    public boolean lStick() {
        return _joystick.getRawButton(9);
    }

    public boolean rStick() {
        return _joystick.getRawButton(10);
    }

    public boolean dUp() {
        return _joystick.getPOV() == 0;
    }

    public boolean dUpRight() {
        return _joystick.getPOV() == 45;
    }

    public boolean dRight() {
        return _joystick.getPOV() == 90;
    }

    public boolean dDownRight() {
        return _joystick.getPOV() == 135;
    }

    public boolean dDown() {
        return _joystick.getPOV() == 180;
    }

    public boolean dDownLeft() {
        return _joystick.getPOV() == 225;
    }

    public boolean dLeft() {
        return _joystick.getPOV() == 270;
    }

    public boolean dUpLeft() {
        return _joystick.getPOV() == 315;
    }
}
