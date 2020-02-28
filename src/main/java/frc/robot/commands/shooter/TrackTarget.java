package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.components.*;
import frc.robot.subsystems.shooter.*;

public class TrackTarget extends ParallelCommandGroup {
    private final Shooter _shooter;

    public TrackTarget(Shooter shooter) {
        this.addRequirements(shooter.turret, shooter.hood, shooter.pitchingWheel);
        _shooter = shooter;

        this.addCommands(
            createTurretTracker(),
            createHoodTracker(),
            createPitchingWheelTracker());
    }

    @Override
    public void initialize() {
        _shooter.limelight.setLedMode(3);
    }

    @Override
    public void end(boolean interrupted) {
        _shooter.limelight.setLedMode(1);
    }

    private Command createTurretTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {
                double xtarget = _shooter.limelight.xTarget();
                _shooter.turret.setRelativePosition(-xtarget);
                if (!_shooter.turret.isEnabled()) {
                    _shooter.turret.enable();
                }
            } else {
                _shooter.turret.setPower(0.0);
            }
        }, _shooter.turret);
    }

    private Command createHoodTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {
                final var area = _shooter.limelight.areaTarget();
                double angle;
                if (area > 3.0) {
                    angle = 42.0;
                }
                else {
                    angle = 61.5 - (6.515 * area);
                }
                _shooter.hood.setSetpoint(angle);
                if (!_shooter.hood.isEnabled()) {
                    _shooter.hood.enable();
                }
            }
        }, _shooter.hood);
    }

    private Command createPitchingWheelTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {
                final var area = _shooter.limelight.areaTarget();
                double rpm;
                if (area < 0.255) { rpm = 7300; }
                else { rpm = 7384.889 + (-336 * area); }
                _shooter.pitchingWheel.setRPM(rpm);
            }
        }, _shooter.pitchingWheel);
    }
}
