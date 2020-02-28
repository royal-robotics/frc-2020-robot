package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.intake.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.shooter.*;

public class TargetAndShoot extends ParallelDeadlineGroup {
    public TargetAndShoot(Intake intake, Shooter shooter) {
        super(new ShootWhenReady(intake, shooter), new TrackTarget(shooter));
    }
}
