package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import frc.robot.examplecode.*;
import frc.robot.libs.controls.*;

public class Robot extends TimedRobot {
  private final LogitechController _controller;
  private final Neomotor _motor2;
  private final W2812bLedStrip _ledStrip;

  public Robot() {
    final var controller = new Joystick(0);
    _controller = new LogitechController(controller, 0.1);
    _motor2 = new Neomotor(5, MotorType.kBrushless);
    _ledStrip = new W2812bLedStrip(controller, 9);
  }

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopPeriodic() {
    final double motorSpeed = _controller.lYAxis();
    _motor2.set(motorSpeed);
  }
}
