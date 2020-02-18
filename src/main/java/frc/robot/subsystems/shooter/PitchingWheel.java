package frc.robot.subsystems.shooter;

import com.revrobotics.*;
import frc.robot.*;
import frc.robot.subsystems.RoyalSubsystem;

public class PitchingWheel extends RoyalSubsystem {
    private final CANSparkMax _shooter;
    private final CANEncoder _shooterEncoder;

    public PitchingWheel() {
        _shooter = Components.Shooter.shooterWheel;
        _shooterEncoder = _shooter.getEncoder();
    }

    public void setPower(double power) {
        _shooter.set(power);
    }
}
