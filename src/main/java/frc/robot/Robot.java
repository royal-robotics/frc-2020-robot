/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
  private Joystick _controller;
  private static final int kCanID = 1;
  private static final MotorType kMotorType = MotorType.kBrushless;
  private static final AlternateEncoderType kAltEncType = AlternateEncoderType.kQuadrature;
  private static final int kCPR = 8192;
  
  private CANSparkMax m_motor;
  private CANPIDController m_pidController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
        
  private CANEncoder m_alternateEncoder;

  @Override
  public void robotInit() {
    m_motor = new CANSparkMax(kCanID, kMotorType);
    m_alternateEncoder = m_motor.getAlternateEncoder(kAltEncType, kCPR);
    

    m_pidController = m_motor.getPIDController();
    m_pidController.setFeedbackDevice(m_alternateEncoder);

    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }

  @Override
  public void teleopPeriodic() {
    if(_controller.getRawButton(1)){   
      setEncoderValues(100);
    }
    else if(_controller.getRawButton(2)){
      setEncoderValues(-100);
    }
    else {
      setEncoderValues(0);
    }
  }

  public void setEncoderValues(double speed) {
    m_motor.set(speed);
    SmartDashboard.putNumber("Encoder Position", m_alternateEncoder.getPosition());
  }
}
