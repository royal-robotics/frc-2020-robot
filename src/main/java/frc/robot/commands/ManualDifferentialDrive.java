package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.libs.controls.ControlsFactory;
import frc.libs.controls.Controllers.Controller;
import frc.libs.controls.Controllers.Logitech310Axis;
import frc.robot.subsystems.*;

public class ManualDifferentialDrive extends CommandBase
{
    private final DrivebaseSubsystem _drivebase;
    private final Axis _throttle;
    private final Axis _steer;

    public ManualDifferentialDrive(DrivebaseSubsystem drivebase, ControlsFactory controlsFactory) {
        _drivebase = drivebase;
        addRequirements(_drivebase);

        _throttle = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
        _steer = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickX);
    }

	@Override
    public void execute()
    {
        final var steerDampend = _steer.get() * 0.75;
        _drivebase.setPower(_throttle.get() + steerDampend, _throttle.get() - steerDampend);
    }
}
