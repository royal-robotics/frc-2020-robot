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
    private final Limelight _limelight; // TODO: Move this into a command

    public Shooter() {
        turret = new Turret();
        hood = new Hood();
        pitchingWheel = new PitchingWheel();
        _limelight = Components.Camera.limelight;

        bindCommands();
    }

    public void bindCommands() {
        turret.setDefaultCommand(new TurretDefault(turret));
        hood.setDefaultCommand(new HoodDefault(hood));
        pitchingWheel.setDefaultCommand(new PitchingWheelDefault(pitchingWheel));

        final var moveTurrent = new TurretTracker(turret);
        final var setPitchingWheel = new RunCommand(() -> {
            if (_limelight.hasTarget()) {
                final var area = _limelight.areaTarget();
                final var rpm = 7300.0 - (300.0 * area);
                pitchingWheel.setRPM(rpm);
            }
        }, pitchingWheel);
        final var setHood = new RunCommand(() -> {
            if (_limelight.hasTarget()) {
                final var area = _limelight.areaTarget();
                final var angle = 61.5 - (0.515 * area);
                hood.setSetpoint(angle);
                if (!hood.isEnabled()) {
                    hood.enable();
                }
            }
        }, hood);
        Controls.Turret.autoTrackShooter.whenHeld(moveTurrent.alongWith(setPitchingWheel).alongWith(setHood));
    }
}
