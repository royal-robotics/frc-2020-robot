package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.libs.components.Limelight;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.commands.defaults.TurretTracker;
import frc.robot.commands.shooter.*;

public class Shooter {
    public final Turret turret;
    public final Hood hood;
    public final PitchingWheel pitchingWheel;
    public final Limelight limelight;

    public Shooter() {
        turret = new Turret();
        hood = new Hood();
        pitchingWheel = new PitchingWheel();
        limelight = Components.Camera.limelight;

        bindCommands();
    }

    public boolean readyToShoot() {
        final var turretReady = turret.isEnabled() && turret.isAtSetpoint();
        final var hoodReady = hood.isEnabled() && hood.isAtSetpoint();
        final var pitchingWheelReady = pitchingWheel.onRPMTarget(0.05);
        return turretReady && hoodReady && pitchingWheelReady;
    }

    public void bindCommands() {
        turret.setDefaultCommand(new TurretDefault(turret));
        hood.setDefaultCommand(new HoodDefault(hood));
        pitchingWheel.setDefaultCommand(new PitchingWheelDefault(pitchingWheel));

        final var moveTurrent = new TurretTracker(turret);
        final var setPitchingWheel = new RunCommand(() -> {
            if (limelight.hasTarget()) {
                final var area = limelight.areaTarget();
                double rpm;
                if (area < 0.255) { rpm = 7300; }
                else { rpm = 7384.889 + (-336 * area); }
                pitchingWheel.setRPM(rpm);
            }
        }, pitchingWheel);
        final var setHood = new RunCommand(() -> {
            if (limelight.hasTarget()) {
                final var area = limelight.areaTarget();
                double angle;
                if (area > 3.0) {
                    angle = 42.0;
                }
                else {
                    angle = 61.5 - (6.515 * area);
                }
                hood.setSetpoint(angle);
                if (!hood.isEnabled()) {
                    hood.enable();
                }
            }
        }, hood);
        Controls.Turret.autoTrackShooter.whenHeld(moveTurrent.alongWith(setPitchingWheel, setHood));
    }
}
