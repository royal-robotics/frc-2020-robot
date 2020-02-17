package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.robot.Controls;
import frc.robot.commands.MoveTurretAngle;
import frc.robot.subsystems.turret.*;

public class TurretControl extends CommandBase {
    private final TurretSubsystem _turret;

    public TurretControl(TurretSubsystem turret) {
        addRequirements(turret);
        _turret = turret;

        Controls.Turret.turnToCenter.whenPressed(new MoveTurretAngle(_turret, 0));
        Controls.Turret.turnToRight.whenPressed(new MoveTurretAngle(_turret, 45));
        Controls.Turret.turnToLeft.whenPressed(new MoveTurretAngle(_turret, -45));
    }

	@Override
    public void execute() {
    }
}
