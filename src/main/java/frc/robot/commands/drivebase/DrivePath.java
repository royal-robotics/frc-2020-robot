package frc.robot.commands.drivebase;

import java.util.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.drivebase.*;

public abstract class DrivePath extends SequentialCommandGroup {
    private final DrivebaseSubsystem _drivebase;
    private final boolean _inverted;

    private static final double StaticVolts = 0.187;
    private static final double VoltSecondsPerMeter = 2.55;
    private static final double VoltSecondsSquaredPerMeter = 0.543;
    private static final SimpleMotorFeedforward MotorFeedforward = new SimpleMotorFeedforward(StaticVolts, VoltSecondsPerMeter, VoltSecondsSquaredPerMeter);

    private static final double MetersPerInch =  0.0254;
    private static final double kTrackwidth = 25.0 * MetersPerInch; // TODO: Get a more accurate width number from SolidWorks.
    protected static final DifferentialDriveKinematics DriveKinematics = new DifferentialDriveKinematics(kTrackwidth);

    private static final double MaxVoltage = 10.0;
    protected static final  DifferentialDriveVoltageConstraint DriveVoltageConstraint = new DifferentialDriveVoltageConstraint(MotorFeedforward, DriveKinematics, MaxVoltage);

    private static final double RamseteB = 2.0;
    private static final double RamseteZeta = 0.7;

    private static final double P_DriveVelocity = 0.01;
    private static final double I_DriveVelocity = 0.0;
    private static final double D_DriveVelocity = 0.0;

    public DrivePath(DrivebaseSubsystem drivebase, boolean inverted) {
        _drivebase = drivebase;
        _inverted = inverted;
    }
    protected abstract Trajectory getTrajectory();

    @Override
    public void initialize() {
        this.addCommands(
            new RamseteCommand(
                getTrajectory(),
                () -> _drivebase.getPose(_inverted),
                new RamseteController(RamseteB, RamseteZeta),
                MotorFeedforward,
                DriveKinematics,
                () -> _drivebase.getWheelSpeeds(_inverted),
                new PIDController(P_DriveVelocity, I_DriveVelocity, D_DriveVelocity),
                new PIDController(P_DriveVelocity, I_DriveVelocity, D_DriveVelocity),
                (leftVolts, rightVolts) -> _drivebase.setVolts(leftVolts, rightVolts, _inverted),
                _drivebase));

        super.initialize();
        _drivebase.setBreakMode(true);
    }
}
