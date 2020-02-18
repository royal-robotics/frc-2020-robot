package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.PWM;
import frc.robot.*;
import frc.robot.subsystems.*;

public class Hood extends RoyalSubsystem {
    private final PWM _hood;

    public Hood() {
        _hood = Components.Shooter.hood;
    }

    public void setPower(double power) {
        _hood.setSpeed(power);
    }
}
