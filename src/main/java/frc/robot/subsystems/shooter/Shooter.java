package frc.robot.subsystems.shooter;

import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.commands.shooter.*;

public class Shooter {
    public final Turret turret;
    public final Hood hood;
    public final PitchingWheel pitchingWheel;

    public Shooter() {
        turret = new Turret();
        hood = new Hood();
        pitchingWheel = new PitchingWheel();

        bindCommands();
    }

    public void bindDefaultCommands() {
        turret.setDefaultCommand(new TurretDefault(turret));
        hood.setDefaultCommand(new HoodDefault(hood));
        pitchingWheel.setDefaultCommand(new PitchingWheelDefault(pitchingWheel));
    }

    public void bindCommands() {
        Controls.Turret.autoTrackShooter.whenHeld(new MoveTurretAngle(turret, 0.0));
    }
}
