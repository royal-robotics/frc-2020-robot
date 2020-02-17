package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class TurretSubsystem extends RoyalSubsystem {
    private final WPI_TalonSRX _platform;
    private final PWM _hood;
    private final CANSparkMax _shooter;
    private final CANEncoder _shooterEncoder;

    public TurretSubsystem() {
        _platform = Components.Turret.platform;
        _hood = Components.Turret.hood;
        _shooter = Components.Turret.shooterWheel;

        _shooterEncoder = _shooter.getEncoder();
    }

    public void turnPlatform(double power) {
        _platform.set(ControlMode.PercentOutput, power);
    }

    public void angleHoode(double speed) {
        _hood.setSpeed(speed);
    }

    public void setShooterPower(double power) {
        _shooter.set(power);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret/Shooter/Velocity", _shooterEncoder.getVelocity());
    }
}
