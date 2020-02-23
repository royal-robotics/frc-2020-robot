package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class Intake extends RoyalSubsystem {
    private final WPI_TalonSRX _ballIn;
    private final WPI_TalonSRX _ballConveyor;
    private final DigitalInput _ballSensorBottom;
    private final DigitalInput _ballSensorTop;

    public Intake() {
        _ballIn = Components.Intake.ballIn;
        _ballConveyor = Components.Intake.conveyer;
        _ballSensorBottom = Components.Intake.ballSensorBottom;
        _ballSensorTop = Components.Intake.ballSensorTop;

        _ballConveyor.setInverted(true);
    }

    public void setIntakePower(double power) {
        _ballIn.set(ControlMode.PercentOutput, power);
    }

    public void setConveyorPower(double power){
        _ballConveyor.set(ControlMode.PercentOutput, power);
    }

    public boolean isBallAtTop() {
        return !_ballSensorTop.get();
    }

    public boolean isBallAtBottom() {
        return !_ballSensorBottom.get();
    }

    @Override
    public void periodic() {
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Intake/BallIn/Power", _ballIn.get());
        SmartDashboard.putNumber("Intake/Conveyor/Power", _ballConveyor.get());
        SmartDashboard.putBoolean("Intake/Conveyor/BottomSensor", isBallAtBottom());
        SmartDashboard.putBoolean("Intake/Conveyor/TopSensor", isBallAtTop());
    }
}
