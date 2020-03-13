package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.MoveHoodAngle;
import frc.robot.commands.MoveTurretAngle;
import frc.robot.commands.drivebase.DriveStraight;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.drivebase.DrivebaseSubsystem;
import frc.robot.subsystems.shooter.*;

public class WallShooter extends ParallelCommandGroup {
    private final Shooter _shooter;
    private final DrivebaseSubsystem _drivebase;
    private final Intake _intake;

    private boolean atHoodAngle = false;
    private boolean atTurretAngle = false;

    public WallShooter(Shooter shooter, DrivebaseSubsystem drivebase, Intake intake) {
        _shooter = shooter;
        _drivebase = drivebase;
        _intake = intake;

        this.addCommands(
            new DriveStraight(_drivebase, 0.635).andThen(() -> _drivebase.setPower(0.0, 0.0)).andThen(setShooting()),
            new MoveHoodAngle(_shooter.hood, 42.5).andThen(() -> atHoodAngle = true),
            new MoveTurretAngle(_shooter.turret, 0.0).andThen(() -> atTurretAngle = true),
            setRPM());
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        atHoodAngle = false;
        atTurretAngle = false;
    }

    private Command setRPM() {
        return new RunCommand(() -> {
            _shooter.pitchingWheel.setRPM(6500);
        }, _shooter.pitchingWheel);
    }

    private Command setShooting() {
        return new RunCommand(() -> {
            if (atHoodAngle && atTurretAngle && _shooter.pitchingWheel.onRPMTarget(0.05)) {
                _intake.setConveyorPower(1.0);
                _intake.setIntakePower(0.0);
            }
        }, _intake);
    }
}
