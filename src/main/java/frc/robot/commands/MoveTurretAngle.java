package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.shooter.*;

public class MoveTurretAngle extends CommandBase {
    private final Turret _turret;
    private final double _angle;

    public MoveTurretAngle(Turret turret, double angle) {
        addRequirements(turret);
        _turret = turret;
        _angle = angle;
    }

    @Override
    public void initialize() {
        _turret.setSetpoint(_angle);
        _turret.enable();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return _turret.isAtSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        _turret.disable();
    }
}
