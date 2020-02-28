package frc.robot.subsystems.shooter;

import frc.libs.components.Limelight;
import frc.robot.*;
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

        Controls.Turret.autoTrackShooter.whenHeld(new TrackTarget(this));
    }
}
