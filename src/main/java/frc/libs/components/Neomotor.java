package frc.libs.components;

import com.revrobotics.*;

public class Neomotor {

    private final CANSparkMax _motor;
    private final CANEncoder _encoder;
    private final CANPIDController _pidController;

    public Neomotor(CANSparkMax motor) {
        _motor = motor;
        _encoder = _motor.getEncoder();
        _pidController = _motor.getPIDController();
        this.configurePidController();
    }

    private void configurePidController() {
        _pidController.setP(5e-5);
        _pidController.setI(1e-6);
        _pidController.setD(0);
        _pidController.setIZone(0);
        _pidController.setFF(0);
        _pidController.setOutputRange(-1, 1);
    }

    public void setSpeed(double percentSpeed) {
        // Set target rpm for pid control
        final var maxRPM = 5700.0;
        final var targetRpm = percentSpeed * maxRPM;
        _pidController.setReference(targetRpm, ControlType.kVelocity);

        // Print actual motor output, and current shaft rpm
        // final var df2 = new DecimalFormat("#.##");
        //System.out.printf("%f %f", df2.format(_motor.get()), _encoder.getVelocity());
    }

    public void setVoltage(double voltage) {
        _pidController.setReference(voltage, ControlType.kVoltage);
    }

    public void setPosition(double position) {
        _pidController.setReference(position, ControlType.kPosition);
    }

    public double getVelocity()
    {
        return _encoder.getVelocity();
    }

    public double getPosition()
    {
        return _encoder.getPosition();
    }

    public void resetPosition()
    {
        _encoder.setPosition(0);
    }
}
