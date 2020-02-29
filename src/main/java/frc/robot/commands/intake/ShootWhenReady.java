package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.shooter.*;

public class ShootWhenReady extends CommandBase {
    private final Intake _intake;
    private final Shooter _shooter;

    public ShootWhenReady(Intake intake, Shooter shooter) {
        addRequirements(intake);
        _intake = intake;
        _shooter = shooter;
    }

    @Override
    public void execute() {
        if (_shooter.readyToShoot()) {
            _intake.setIntakePower(1.0);
            _intake.setConveyorPower(1.0);
        } else {
            _intake.setIntakePower(0.0);
            _intake.setConveyorPower(0.0);
        }
    }

    @Override
    public boolean isFinished() {
        return _intake.getBallCount() <= 0;
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setIntakePower(0.0);
        _intake.setConveyorPower(0.0);
    }
}
