package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.*;

import frc.examplecode.*;

public class Robot extends TimedRobot {
    private final LogitechController _controller;
    private final W2812bLedStrip _ledStrip;
    private final RobotContainer robot_container;

    public Robot() {
        final var controller = new Joystick(0);
        _controller = new LogitechController(controller, 0.1);
        _ledStrip = new W2812bLedStrip(controller, 9);
        robot_container = new RobotContainer();
    }

    @Override
    public void robotInit() {
    }

    @Override
    public void robotPeriodic()
    {
        CommandScheduler.getInstance().run();
        Shuffleboard.update();
    }

    @Override
    public void teleopPeriodic() {
    }
}
