package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.commands.*;
import frc.robot.subsystems.shooter.*;

public class ShooterControl extends CommandBase {
    private final ShooterSubsystem _shooter;

    public ShooterControl(ShooterSubsystem shooter) {
        addRequirements(shooter);
        _shooter = shooter;

        // TODO: Cancel when released
        Controls.Turret.autoTrackShooter.whenPressed(new MoveTurretAngle(_shooter, 0.0));
    }

	@Override
    public void execute() {
        final var platformPower = Controls.Turret.movePlatform.get();
        _shooter.turret.setPower(-platformPower);


        final var hoodPower = Controls.Turret.moveHood.get();
        _shooter.setHoodPower(hoodPower);

        final var wheelPower = Controls.Turret.wheelThrottle.get();
        _shooter.setShooterPower(wheelPower);
    }
}
