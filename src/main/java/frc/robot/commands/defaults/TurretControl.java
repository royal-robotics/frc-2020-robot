package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.turret.*;

public class TurretControl extends CommandBase {
    private final TurretSubsystem _turret;

    public TurretControl(TurretSubsystem turret) {
        addRequirements(turret);
        _turret = turret;

        // TODO: Cancel when released
        Controls.Turret.autoTrackShooter.whenPressed(new MoveTurretAngle(_turret, 0.0));
    }

	@Override
    public void execute() {
        // System.out.println("Default");
        final var platformPower = Controls.Turret.movePlatform.get();
        _turret.platform.setPower(-platformPower);


        final var hoodPower = Controls.Turret.moveHood.get();
        _turret.setHoodPower(hoodPower);

        final var wheelPower = Controls.Turret.wheelThrottle.get();
        _turret.setShooterPower(wheelPower);
    }
}
