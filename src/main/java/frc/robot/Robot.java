/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import edu.wpi.first.wpilibj.smartdashboard.*;
import examplecode.*;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Robot extends TimedRobot {
  private Joystick _controller;
  private Neomotor motor2;
  private Neomotor motor4;

  @Override
  public void robotInit() {
    _controller = new Joystick(0);
    motor2 = new Neomotor(2, MotorType.kBrushless);
    motor4 = new Neomotor(4, MotorType.kBrushless);
  }

  @Override
  public void teleopPeriodic() {
    double direction = _controller.getRawAxis(1);
    motor2.Move(direction);
    motor4.Move(direction);
    motor2.ReadEncoder();
    motor4.ReadEncoder();
  }
}
