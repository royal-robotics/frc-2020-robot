package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.shooter.*;

public class TrackTarget extends ParallelCommandGroup {
    private final Shooter _shooter;

    public TrackTarget(Shooter shooter) {
        _shooter = shooter;

        this.addCommands(
            createTurretTracker(),
            createHoodTracker(),
            createPitchingWheelTracker());
    }

    @Override
    public void initialize() {
        super.initialize();
        _shooter.limelight.setLedMode(3);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        _shooter.limelight.setLedMode(1);
    }

    double xTargetLast = 0.0;
    double xTargetLast2 = 0.0;
    double xTargetLast3 = 0.0;
    private Command createTurretTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {
                double xtarget = _shooter.limelight.xTarget();
                // _shooter.turret.setRelativePosition(-xtarget);
                _shooter.turret.setRelativePosition(-((xtarget + xTargetLast + xTargetLast2) / 3.0));
                if (!_shooter.turret.isEnabled()) {
                    _shooter.turret.enable();
                }

                xTargetLast3 = xTargetLast2;
                xTargetLast2 = xTargetLast;
                xTargetLast = xtarget;

            } else {
                _shooter.turret.setPower(0.0);
            }


        }, _shooter.turret);
    }

    private double _lastArea = 0.0;
    private double _lastArea2 = 0.0;
    private double _lastArea3 = 0.0;
    private Command createHoodTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {

                final var area = _shooter.limelight.areaTarget();
                final var areaAverage = (area + _lastArea + _lastArea2 + _lastArea3) / 4.0;
                final var angle = 61.5 - (0.515 * areaAverage);
                _shooter.hood.setSetpoint(angle);
                if (!_shooter.hood.isEnabled()) {
                    _shooter.hood.enable();
                }

                _lastArea3 = _lastArea2;
                _lastArea2 = _lastArea;
                _lastArea = area;
            }
        }, _shooter.hood);
    }

    private Command createPitchingWheelTracker() {
        return new RunCommand(() -> {
            if (_shooter.limelight.hasTarget()) {
                final var area = _shooter.limelight.areaTarget();
                final var rpm = 7200.0 - (350.0 * area);
                _shooter.pitchingWheel.setRPM(rpm);
            }
        }, _shooter.pitchingWheel);
    }
}
