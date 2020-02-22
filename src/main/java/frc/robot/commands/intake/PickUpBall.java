package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class PickUpBall extends SequentialCommandGroup {
    public PickUpBall(Intake intake) {
        this.addCommands(new StartIntake(intake), new StartConveyor(intake), new WaitCommand(0.2), new StopAllIntake(intake));
    }
}
