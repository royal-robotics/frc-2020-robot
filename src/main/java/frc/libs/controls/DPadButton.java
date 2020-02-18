package frc.libs.controls;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.button.*;

public class DPadButton extends Button {
    private final Joystick _joystick;
    private final Direction _direction;

    public DPadButton(Joystick joystick, Direction direction) {
        _joystick = joystick;
        _direction = direction;
    }

    public boolean get() {
        int dPadValue = _joystick.getPOV();
        return dPadValue == _direction.angle;
    }

    public static enum Direction {
        Up(0),
        TopRight(45),
        Right(90),
        BottomRight(135),
        Down(180),
        BottomLeft(225),
        Left(270),
        TopLeft(315);

        int angle;

        private Direction(int direction) {
            this.angle = direction;
        }
    }
}
