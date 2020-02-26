package frc.robot.autonomous.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.*;
import frc.libs.components.Limelight;
import frc.robot.autonomous.AutoModeBase;
import frc.robot.commands.defaults.TurretTracker;
import frc.robot.subsystems.*;
import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.shooter.*;
import frc.robot.Components;

public class TestAuto extends AutoModeBase {
    private final DrivebaseSubsystem _drivebase;
    private final Shooter _shooter;
    private final Intake _intake;
    private final Limelight _limelight;

    public TestAuto(DrivebaseSubsystem drivebase, Shooter shooter, Intake intake, Limelight limelight) {
        addRequirements(drivebase, shooter.pitchingWheel, shooter.turret, shooter.hood, intake);
        _drivebase = drivebase;
        _shooter = shooter;
        _intake = intake;
        _limelight = limelight;

        this.addCommands(new TurretTracker(shooter.turret, true),
                         new RunPitchingWheel(_shooter.pitchingWheel, _shooter.hood, _limelight),
                         new RunConveyor(_intake).withTimeout(7.0),
                         new StopPitchingWheel(_shooter.pitchingWheel) );
    }

    private class RunPitchingWheel extends CommandBase{
        private final PitchingWheel _pitchingWheel;
        private final Hood _hood;
        private final Limelight _limelight;

        public RunPitchingWheel(PitchingWheel pitchingWheel, Hood hood, Limelight limelight) {
            addRequirements(pitchingWheel, hood);
            _pitchingWheel = pitchingWheel;
            _hood = hood;
            _limelight = limelight;
        }

        @Override
        public void initialize() {
            final var area = _limelight.areaTarget();
            final var rpm = 7300.0 - (300.0 * area);
            _pitchingWheel.setRPM(rpm);

            final var angle = 61.5 - (0.515 * area);
            _hood.setSetpoint(angle);
            if (!_hood.isEnabled()) {
                _hood.enable();
            }
        }

        @Override
        public boolean isFinished() {
            return _pitchingWheel.atRPM(0.025) && _hood.isAtSetpoint();
        }
    }

    private class RunConveyor extends CommandBase {
        private final Intake _intake;
        private int _counter = 0;
        private boolean _ballSeen = Components.Intake.ballSensorTop.get();

        public RunConveyor(Intake intake) {
            addRequirements(intake);
            _intake = intake;
        }

        @Override
        public void initialize() {
            _intake.setConveyorPower(1.0);
        }

        @Override
        public void execute() {
            if (_ballSeen != Components.Intake.ballSensorTop.get()) {
                _counter++;
                _ballSeen = !_ballSeen;
            }
        }

        @Override
        public boolean isFinished() {
            return (_counter >= 6);
        }

        @Override
        public void end(boolean interrupted) {
            _intake.setConveyorPower(0.0);
        }
    }

    private class StopPitchingWheel extends CommandBase {
        private final PitchingWheel _pitchingWheel;

        public StopPitchingWheel (PitchingWheel pitchingWheel) {
            addRequirements(pitchingWheel);
            _pitchingWheel = pitchingWheel;
        }

        @Override
        public void initialize() {
            _pitchingWheel.setPower(0.0);
        }

        @Override
        public boolean isFinished() {
            return true;
        }
    }

}
