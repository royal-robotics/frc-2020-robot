package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.shooter.TrackTarget;
import frc.robot.subsystems.*;
import frc.robot.subsystems.shooter.*;

public class TargetAndShoot extends ParallelCommandGroup {

    public TargetAndShoot(Intake intake, Shooter shooter) {
        this.addCommands(new TrackTarget(shooter));
        this.addCommands(new ShootWhenReady(intake, shooter));
    }

    private class ShootWhenReady extends CommandBase {
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
            // TODO: Count balls, add timeout wrapper with race
            return false;
        }

        @Override
        public void end(boolean interrupted) {
            _intake.setIntakePower(0.0);
            _intake.setConveyorPower(0.0);
        }
    }
}
