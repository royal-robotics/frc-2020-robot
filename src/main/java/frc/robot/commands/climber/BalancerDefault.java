package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.*;
import frc.robot.subsystems.climber.*;

public class BalancerDefault extends CommandBase {
    private final Balancer _balancer;
    private final Button _balanceLeft;
    private final Button _balanceRight;

    public BalancerDefault(Balancer balancer) {
        addRequirements(balancer);

        _balancer = balancer;
        _balanceLeft = Controls.Climber.balanceLeft;
        _balanceRight = Controls.Climber.balanceRight;

        _balancer.disable();
    }

    @Override
    public void execute() {
        if (_balanceLeft.get()) {
            _balancer.setPower(-1.0);
        } else if (_balanceRight.get()) {
            _balancer.setPower(1.0);
        } else {
            _balancer.setPower(0.0);
        }
    }
}
