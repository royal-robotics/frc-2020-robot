package frc.examplecode;

import edu.wpi.first.wpilibj.*;

public class Revservo {

    private final PWM _servo;

    public Revservo(int channel) {
        _servo = new PWM(channel);
    }

    public void move(double direction) {
        if (direction > 0.1) {
            _servo.setSpeed(1);
        } else if (direction < -0.1) {
            _servo.setSpeed(-1);
        } else {
            _servo.setSpeed(0);
        }
    }
}
