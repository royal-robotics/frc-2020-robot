package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

import frc.robot.examplecode.*;
import frc.robot.libs.controls.*;

public class Robot extends TimedRobot {
  private LogitechController _controller;
  private Neomotor motor2;
  private Neomotor motor4;

  @Override
  public void robotInit() {
    _controller = new LogitechController(0, 0.1);
    motor2 = new Neomotor(2, MotorType.kBrushless);
    motor4 = new Neomotor(4, MotorType.kBrushless);
  }

  @Override
  public void teleopPeriodic() {
    double direction = _controller.LYAxis();
    motor2.Move(direction);
    motor4.Move(direction);
    motor2.ReadEncoder();
    motor4.ReadEncoder();
  }
}
