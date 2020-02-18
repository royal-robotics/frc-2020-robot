package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.shooter.*;

public class MoveTurretAngle extends CommandBase {
    private final ShooterSubsystem _shooter;
    private final double _angle;

    public MoveTurretAngle(ShooterSubsystem shooter, double angle) {
        addRequirements(shooter);
        _shooter = shooter;
        _angle = angle;
    }

    @Override
    public void initialize() {
        System.out.println("auto move init! " + _angle);
        _shooter.turret.reset();
        _shooter.turret.setTargetAngle(_angle);
    }

    @Override
    public void execute() {
        System.out.println("auto move!");
    }

    @Override
    public boolean isFinished() {
        System.out.println("At target? " + _shooter.turret.atTargetAngle());
        return _shooter.turret.atTargetAngle();
    }

    @Override
    public void end(boolean interrupted) {
        _shooter.turret.stop();
    }
}
