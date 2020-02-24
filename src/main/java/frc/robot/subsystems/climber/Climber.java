package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.climber.*;
import frc.robot.*;

public class Climber {
    public final Elevator elevator;
    public final Balancer balancer;
    private final Lock lock;

    private final Button _toggleLock;

    public Climber() {
        elevator = new Elevator();
        balancer = new Balancer();
        lock = new Lock();

        _toggleLock = Controls.Climber.toggleLock;

        bindCommands();
    }

    public void bindCommands() {
        Controls.Climber.quickMoveElevatorBottom.whileHeld(new ElevatorMoveCommand(elevator, 0.0));
        Controls.Climber.quickMoveElevatorTop.whileHeld(new ElevatorMoveCommand(elevator, 29.0));
        _toggleLock.toggleWhenPressed(new InstantCommand(() -> lock.toggle()));
    }
}
