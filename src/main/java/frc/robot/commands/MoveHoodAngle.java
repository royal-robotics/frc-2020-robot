package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.shooter.*;

public class MoveHoodAngle extends CommandBase {
    private final Hood _hood;
    private final double _angle;

    public MoveHoodAngle(Hood hood, double angle) {
        addRequirements(hood);
        _hood = hood;
        _angle = angle;
    }

    @Override
    public void initialize() {
        _hood.setSetpoint(_angle);
        _hood.enable();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return _hood.isAtSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        _hood.disable();
    }
}
