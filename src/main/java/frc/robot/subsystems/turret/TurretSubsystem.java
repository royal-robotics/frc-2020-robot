package frc.robot.subsystems.turret;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.subsystems.*;

public class TurretSubsystem extends RoyalSubsystem {
    public final Platform platform;
    private final PWM _hood;
    private final CANSparkMax _shooter;
    private final CANEncoder _shooterEncoder;

    public TurretSubsystem() {
        platform = new Platform();
        _hood = Components.Turret.hood;
        _shooter = Components.Turret.shooterWheel;

        _shooterEncoder = _shooter.getEncoder();
    }
    public void angleHoode(double speed) {
        _hood.setSpeed(speed);
    }

    public void setShooterPower(double power) {
        _shooter.set(power);
    }

    @Override
    public void periodic() {
        platform.updateControlLoop();
        SmartDashboard.putNumber("Turret/Shooter/Velocity", _shooterEncoder.getVelocity());
    }
}
