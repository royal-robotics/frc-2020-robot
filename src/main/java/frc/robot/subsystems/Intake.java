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

    private boolean _autoBallIntakeEnabled = false;

    public Intake() {
        _ballIn = Components.Intake.ballIn;
        _ballConveyor = Components.Intake.conveyer;
        _ballSensorBottom = Components.Intake.ballSensorBottom;
        _ballSensorTop = Components.Intake.ballSensorTop;

        _ballConveyor.setInverted(true);
    }

    public void intakeOn() {
        // The outside roller should always be on while intaking balls.
        _ballIn.set(ControlMode.PercentOutput, 0.8);

        if (_ballSensorTop.get()) {
            // We can't intake more balls if there's one at the top already.
            _ballConveyor.set(ControlMode.PercentOutput, 0.0);
        } else {
            // Only run the intake if there's a ball visible.
            if (_ballSensorBottom.get()) {
                _ballConveyor.set(ControlMode.PercentOutput, 0.8);
            } else {
                _ballConveyor.set(ControlMode.PercentOutput, 0.0);
            }
        }
    }

    public void setIntakePower(double power) {
        _ballIn.set(ControlMode.PercentOutput, power);
    }

    public void setConveyorPower(double power){
        _ballConveyor.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void periodic() {
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Intake/BallIn/Power", _ballIn.get());
        SmartDashboard.putNumber("Intake/Conveyor/Power", _ballConveyor.get());
        SmartDashboard.putBoolean("Intake/Conveyor/BottomSensor", Components.Intake.getBottomBallSensor());
        SmartDashboard.putBoolean("Intake/Conveyor/TopSensor", Components.Intake.getTopBallSensor());
    }
}
