package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.turret.*;

public class MoveTurretAngle extends CommandBase {
    private final TurretSubsystem _turret;
    private final double _angle;

    public MoveTurretAngle(TurretSubsystem turret, double angle) {
        addRequirements(turret);
        _turret = turret;
        _angle = angle;
    }

    @Override
    public void initialize() {
        System.out.println("auto move init! " + _angle);
        _turret.platform.reset();
        _turret.platform.setTargetAngle(_angle);
    }

    @Override
    public void execute() {
        System.out.println("auto move!");
    }

    @Override
    public boolean isFinished() {
        System.out.println("At target? " + _turret.platform.atTargetAngle());
        return _turret.platform.atTargetAngle();
    }

    @Override
    public void end(boolean interrupted) {
        _turret.platform.stop();
    }
}
