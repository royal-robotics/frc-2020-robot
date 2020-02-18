package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PWM;
import frc.robot.*;
import frc.robot.subsystems.*;

public class ShooterSubsystem extends RoyalSubsystem {
    public final Turret turret;
    private final PWM _hood;
    private final CANSparkMax _shooter;
    private final CANEncoder _shooterEncoder;

    public ShooterSubsystem() {
        turret = new Turret();
        _hood = Components.Shooter.hood;
        _shooter = Components.Shooter.shooterWheel;
        _shooterEncoder = _shooter.getEncoder();
    }
    public void setHoodPower(double speed) {
        _hood.setSpeed(speed);
    }

    public void setShooterPower(double power) {
        _shooter.set(power);
    }

    @Override
    public void periodic() {
        turret.updateControlLoop();
        turret.updateDiagnostics();
        // SmartDashboard.putNumber("Turret/Shooter/Velocity", _shooterEncoder.getVelocity());
    }
}
