/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private Joystick _controller;
  
  private WPI_TalonSRX _rightDrive1 = new WPI_TalonSRX(8);
  private WPI_VictorSPX _rightDrive2 = new WPI_VictorSPX(6);
  private WPI_VictorSPX _rightDrive3 = new WPI_VictorSPX(4);

  private WPI_TalonSRX _leftDrive1 = new WPI_TalonSRX(7);
  private WPI_VictorSPX _leftDrive2 = new WPI_VictorSPX(5);
  private WPI_VictorSPX _leftDrive3 = new WPI_VictorSPX(3);
        

  @Override
  public void robotInit() {
    //this is a comment
    _controller = new Joystick(0);

    _leftDrive2.follow(_leftDrive1);
    _leftDrive3.follow(_leftDrive1);

    _rightDrive2.follow(_rightDrive1);
    _rightDrive3.follow(_rightDrive1);
  }

  @Override
  public void teleopPeriodic() {
    //boolean a_pressed= false;
    float speed_modifier = 0.1f;
    /*
    if (_controller.getRawButton(1))
    {
      _leftDrive1.set(-speed_modifier*_controller.getRawAxis(1));
      _rightDrive1.set(speed_modifier*_controller.getRawAxis(5));
    }
    else 
    {
      _leftDrive1.set(-_controller.getRawAxis(1));
      _rightDrive1.set(_controller.getRawAxis(5));
    }
    */
    
    //System.out.println(_controller.getRawAxis(0));
    Boolean toggle;
    //false means normal speed
    //true means speed set to 0.1
    if (_controller.getRawButton(1))
    {
     toggle = false;
    }

    else if (_controller.getRawButton(2))
    {
      toggle = true;
    }
  }

}
