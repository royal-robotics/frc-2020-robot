package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.Axis;
import frc.robot.*;
import frc.robot.subsystems.shooter.*;

public class HoodDefault extends CommandBase {
    private final Hood _hood;
    private final Axis _moveHood;

    // The joystick starts in the deadband position.
    private boolean _wasInDeadband = true;

    public HoodDefault(Hood hood) {
        addRequirements(hood);
        _hood = hood;
        _moveHood = Controls.Turret.moveHood;
    }

	@Override
    public void execute() {
        final var hoodPower = _moveHood.get(true);
        _hood.setPower(hoodPower);

        // If we're entering the deadband state we want to save the current position.
        final var inDeadband = _moveHood.inDeadband();
        if (!_wasInDeadband && inDeadband) {
            _hood.save();
        }

        _wasInDeadband = inDeadband;
    }
}
