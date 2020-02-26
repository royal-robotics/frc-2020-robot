package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.*;
import frc.robot.subsystems.shooter.*;

public class PitchingWheelDefault extends CommandBase {
    private final PitchingWheel _pitchingWheel;

    public PitchingWheelDefault(PitchingWheel pitchingWheel) {
        addRequirements(pitchingWheel);
        _pitchingWheel = pitchingWheel;
    }

	@Override
    public void execute() {
         final var wheelPower = Controls.Turret.wheelThrottle.get();
         if (Controls.Turret.wheelThrottle.inDeadband()) {
            _pitchingWheel.setPower(0.15);
         }
         else {
            _pitchingWheel.setPower(wheelPower);
         }
    }
}
