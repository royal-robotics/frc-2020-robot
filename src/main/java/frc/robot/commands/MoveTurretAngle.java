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
        _turret.platform.setGoalAngle(_angle);
    }

    @Override
    public boolean isFinished() {
        if (_turret.platform.atGoalAngle())
        {
            _turret.platform.stop();
            return true;
        }
        return false;
    }
}
