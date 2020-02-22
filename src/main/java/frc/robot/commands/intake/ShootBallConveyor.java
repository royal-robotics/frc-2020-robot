package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ShootBallConveyor extends CommandBase {
    private final Intake _intake;

    public ShootBallConveyor(Intake intake) {
        _intake = intake;
    }

    @Override
    public void initialize() {
        _intake.setConveyorPower(1.0);
    }

    @Override
    public void end(boolean interrupted) {
        _intake.setConveyorPower(0.0);
    }
}
