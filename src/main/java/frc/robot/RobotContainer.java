package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.climber.ElevatorDefault;
import frc.robot.commands.colorwheel.*;
import frc.robot.commands.drivebase.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.TurretDefault;
import frc.robot.commands.shooter.WallShooter;
import frc.robot.subsystems.*;
import frc.robot.subsystems.climber.*;
import frc.robot.subsystems.colorwheel.*;
import frc.robot.subsystems.drivebase.*;
import frc.robot.subsystems.shooter.*;
import frc.robot.shuffleboard.*;

public class RobotContainer {
    public final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
    public final Intake intake = new Intake();
    public final Shooter shooter = new Shooter();
    public final Climber climber = new Climber();
    public final ColorWheelSubsystem colorWheel = new ColorWheelSubsystem();

    // private final DriverTab driverTab = new DriverTab(drivebase, intake);
    // private final ConfigsTab configsTab = new ConfigsTab(drivebase, intake);
    // private final RawDataTab rawDataTab = new RawDataTab(drivebase, intake);

    public RobotContainer() {
        // Calibrate the gyro once when the robot turns on.
        // It's very important the robot is motionless until
        // this calibration is complete.
        Components.Drivebase.gyro.calibrate();
        Components.Drivebase.gyro.reset();

        drivebase.setDefaultCommand(new DrivebaseDefault(drivebase));
        intake.setDefaultCommand(new IntakeDefault(intake, shooter));
        colorWheel.setDefaultCommand(new ColorWheelDefault(colorWheel));
        shooter.turret.setDefaultCommand(new TurretDefault(shooter.turret));
        climber.elevator.setDefaultCommand(new ElevatorDefault(climber.elevator));

        Controls.Turret.autoTrackProtected.whenHeld(new DriveStraight(drivebase, 0.635));

        SmartDashboard.putData("Reset Drivebase", new InstantCommand(() -> {
            drivebase.reset();
        }));
    }

    public final void storeState() {
        shooter.turret.save();
        shooter.hood.save();
    }
}
