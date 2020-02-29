package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.*;

public class Intake extends RoyalSubsystem {
    private final WPI_TalonSRX _ballIn;
    private final WPI_TalonSRX _ballConveyor;
    private final DigitalInput _ballSensorBottom;
    private final DigitalInput _ballSensorTop;
    private final BallCounter _ballCounter;

    public Intake() {
        _ballIn = Components.Intake.ballIn;
        _ballConveyor = Components.Intake.conveyer;
        _ballSensorBottom = Components.Intake.ballSensorBottom;
        _ballSensorTop = Components.Intake.ballSensorTop;
        _ballCounter = new BallCounter(this, 3);

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

    public int getBallCount() {
        return _ballCounter.get();
    }

    @Override
    public void periodic() {
        updateDiagnostics();
    }

    private void updateDiagnostics() {
        SmartDashboard.putNumber("Intake/BallIn/Power", _ballIn.get());
        SmartDashboard.putBoolean("Intake/BallIn/On", Math.abs(_ballIn.get()) > 0.0);
        SmartDashboard.putNumber("Intake/Conveyor/Power", _ballConveyor.get());
        SmartDashboard.putBoolean("Intake/Conveyor/BottomSensor", isBallAtBottom());
        SmartDashboard.putBoolean("Intake/Conveyor/TopSensor", isBallAtTop());
        SmartDashboard.putNumber("Intake/Conveyor/BallCount", _ballCounter.get());
    }



    private class BallCounter {
        private final Trigger _ballIn;
        private final Trigger _ballOut;
        private int _count;

        public BallCounter(Intake intake, int startingCount) {
            _count = startingCount;

            _ballIn = new Trigger(() -> intake.isBallAtBottom());
            _ballOut = new Trigger(() -> intake.isBallAtTop());

            _ballIn.whenInactive(() -> _count++);
            _ballOut.whenInactive(() -> _count--);
        }

        public int get() {
            return _count;
        }
    }
}
