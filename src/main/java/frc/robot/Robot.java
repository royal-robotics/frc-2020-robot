package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.*;

public class Robot extends TimedRobot {
    private final RobotContainer _robotContainer;

    public Robot() {
        _robotContainer = new RobotContainer();
    }

    @Override
    public void robotInit() {
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Shuffleboard.update();
    }
}
