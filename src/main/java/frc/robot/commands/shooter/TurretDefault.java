package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.shooter.*;

public class TurretDefault extends CommandBase {
    private final Turret _turret;

    public TurretDefault(Turret turret) {
        addRequirements(turret);
        _turret = turret;
    }

	@Override
    public void execute() {
        final var platformPower = Controls.Turret.movePlatform.get();
        _turret.setPower(-platformPower);

        // final var hoodPower = Controls.Turret.moveHood.get();
        // _shooter.setHoodPower(hoodPower);

        // final var wheelPower = Controls.Turret.wheelThrottle.get();
        // _shooter.setShooterPower(wheelPower);
    }
}
