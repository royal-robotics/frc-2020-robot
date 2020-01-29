package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import frc.robot.examplecode.*;
import frc.robot.libs.controls.*;

public class Robot extends TimedRobot {
  private final LogitechController _controller;
  private final Neomotor _motor2;

  public Robot() {
    _controller = new LogitechController(0, 0.1);
    _motor2 = new Neomotor(5, MotorType.kBrushless);
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
