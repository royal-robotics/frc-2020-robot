package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.libs.components.Limelight;
import frc.robot.*;
import frc.robot.subsystems.shooter.Turret;

public class TurretTracker extends CommandBase {
    private final Turret _turret;
    private final Limelight _limelight;
    private final boolean _endOnTarget;

    public TurretTracker(Turret turret) {
        addRequirements(turret);
        _turret = turret;
        _limelight = Components.Camera.limelight;
        _endOnTarget = false;
    }

    public TurretTracker(Turret turret, boolean endOnTarget) {
        addRequirements(turret);
        _turret = turret;
        _limelight = Components.Camera.limelight;
        _endOnTarget = endOnTarget;
    }

    @Override
    public void initialize() {
        _limelight.setLedMode(3);
    }

    @Override
    public void execute() {
        // Turn turret 0.186 degrees per pixel we are off on the x-axis
        // Target takes up 7.98% of the screen at 56.184" away
        // Distance = squirt(25204.3/% of image)
        if (_limelight.hasTarget()) {
            double xtarget = _limelight.xTarget();

            _turret.setRelativePosition(-xtarget);
            if (!_turret.isEnabled()) {
                _turret.enable();
            }
        } else {
        }
    }

    @Override
    public boolean isFinished() {
        return (_endOnTarget && _turret.isAtSetpoint());
    }

    @Override
    public void end(boolean interrupted) {
        _limelight.setLedMode(1);
    }
}
