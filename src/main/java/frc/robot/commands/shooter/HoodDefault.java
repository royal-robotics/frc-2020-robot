package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.subsystems.shooter.*;

public class HoodDefault extends CommandBase {
    private final Hood _hood;

    public HoodDefault(Hood hood) {
        addRequirements(hood);
        _hood = hood;
    }

	@Override
    public void execute() {
        final var hoodPower = Controls.Turret.moveHood.get();
        _hood.setPower(hoodPower);
    }
}
