package frc.libs.models;

public class TankThrottleValues {
    public final double left;
    public final double right;

    public TankThrottleValues(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public TankThrottleValues withScaleFactor(double scaleFactor) {
        return new TankThrottleValues(left * scaleFactor, right * scaleFactor);
    }
}
