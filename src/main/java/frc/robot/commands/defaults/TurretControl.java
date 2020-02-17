package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.robot.Controls;
import frc.robot.subsystems.*;

public class TurretControl extends CommandBase {
    private final TurretSubsystem _turret;
    private final Axis _platformTurner;
    // private final Axis _arcAngler;
    private final Axis _wheelThrottle;

    public TurretControl(TurretSubsystem turret) {
        addRequirements(turret);
        _turret = turret;

        _platformTurner = Controls.Turret.platformTurner;
        // _arcAngler = Controls.Turret.arcAngler;
        _wheelThrottle = Controls.Turret.wheelThrottle;
    }

	@Override
    public void execute() {
        _turret.turnPlatform(_platformTurner.get());
        _turret.setShooterPower(_wheelThrottle.get());
    }
}
