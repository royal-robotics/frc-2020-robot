package frc.robot.commands.manual;

import edu.wpi.first.wpilibj2.command.*;
import frc.libs.controls.*;
import frc.libs.controls.Controllers.*;
import frc.robot.subsystems.*;

public class ManualTankDrive extends CommandBase {
    private final DrivebaseSubsystem _drivebase;
    private final Axis _leftAxis;
    private final Axis _rightAxis;

    public ManualTankDrive(DrivebaseSubsystem driverbase, ControlsFactory controlsFactory) {
        _drivebase = driverbase;
        addRequirements(_drivebase);

        _leftAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.LeftStickY);
        _rightAxis = controlsFactory.createAxis(Controller.Driver, Logitech310Axis.RightStickY);

    }

	@Override
    public void execute() {
        _drivebase.setPower(_leftAxis.get(), _rightAxis.get());
    }
}
