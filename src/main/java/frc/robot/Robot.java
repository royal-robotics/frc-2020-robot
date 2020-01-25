/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.PWM;

public class Robot extends TimedRobot {
  private Joystick _controller;
  private PWM servo;

  @Override
  public void robotInit() {
    _controller = new Joystick(0);
    servo = new PWM(0);
  }

  @Override
  public void teleopPeriodic() {
    double direction = _controller.getRawAxis(0);

    if(direction > 0.1){   
      servo.setSpeed(1);
    }
    else if (direction < -0.1)
    {
      servo.setSpeed(-1);
    }
    else {
      servo.setSpeed(0);
    }
  }
}
