package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.climber.*;
import frc.robot.*;

public class Climber {
    public final Elevator elevator;
    public final Balancer balancer;
    private final PWM _lock; // TODO: This should have its own subsystem?

    private final Button _autoBalanceButton;

    public Climber() {
        elevator = new Elevator();
        balancer = new Balancer();
        _lock = Components.Climber.lock;

        _autoBalanceButton = Controls.Climber.autoBalance;

        bindCommands();
    }

    public void bindCommands() {
        // if (_autoBalanceButton.get()) {
        //     _climber.balancer.enable();
        // } else {
        //     _climber.balancer.disable();
        // }

        elevator.setDefaultCommand(new ElevatorDefault(elevator));

        Controls.Climber.quickMoveElevatorBottom.whenPressed(new ElevatorMoveCommand(elevator, 0.0));
        Controls.Climber.quickMoveElevatorTop.whenPressed(new ElevatorMoveCommand(elevator, 12.0));
    }
}
