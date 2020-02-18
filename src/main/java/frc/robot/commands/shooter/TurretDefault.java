package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.robot.*;
import frc.robot.subsystems.shooter.*;

public class TurretDefault extends CommandBase {
    private final Turret _turret;
    private final Axis _moveTurret;

    // The joystick starts in the deadband position.
    private boolean _wasInDeadband = true;

    public TurretDefault(Turret turret) {
        addRequirements(turret);
        _turret = turret;

        _moveTurret = Controls.Turret.moveTurret;
    }

	@Override
    public void execute() {
        final var platformPower = _moveTurret.get();
        _turret.setPower(-platformPower);

        // If we're entering the deadband state we want to save the current position.
        final var inDeadband = _moveTurret.inDeadband();
        if (!_wasInDeadband && inDeadband) {
            _turret.savePosition();
        }

        _wasInDeadband = inDeadband;
    }
}
