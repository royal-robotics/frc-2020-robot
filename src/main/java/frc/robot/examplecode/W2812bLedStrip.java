package frc.robot.examplecode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/// <remark>There can only be one pwm port configured as an AddressableLed</remark>
public class W2812bLedStrip {

    private final AddressableLED _ledStrip;
    private final AddressableLEDBuffer _ledBuffer;
    private final Joystick _controller;
    private final Notifier _controlLoop;

    private final ColorMatch m_colorMatcher = new ColorMatch();

    /**
     * Note: Any example colors should be calibrated as the user needs, these
     * are here as a basic example.
     */
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a 
     * parameter. The device will be automatically initialized with default 
     * parameters.
     */
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

    private int _rainbowFirstPixelHue = 0;

    public W2812bLedStrip(Joystick controller, int pwmPort) {
        _ledStrip = new AddressableLED(pwmPort);
        _ledBuffer = new AddressableLEDBuffer(150);
        _ledStrip.setLength(_ledBuffer.getLength());

        _controller = controller;
        _controlLoop = initializeControlLoop();
    }

    private Notifier initializeControlLoop() {

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
        
        // Run it once immediately before starting the periodic run
        controlLoop();
    
        // Configure and start the periodic run
        final var controlLoop = new Notifier(() -> controlLoop());
        final var controlLoopIntervalMs = 40.0;
        controlLoop.startPeriodic(controlLoopIntervalMs / 1000.0);
        return controlLoop;

    
    }

    private void controlLoop() {
        fillBuffer();
        _ledStrip.start();
        _ledStrip.setData(_ledBuffer);
    }

    /// <summary>
    /// Fills the buffer with a rainbow pattern that rotates each time the buffer is filled.
    /// </summary>
    // private void fillBuffer() {
    //     // Pressing the right trigger lowers the saturation
    //     var brightness = (int)(255.0 - (_controller.getRawAxis(3) * 255.0));


    //     for (var ledIndex = 0; ledIndex < _ledBuffer.getLength(); ledIndex++) {
    //         // Hue used because its a linear representation of color spectrum
    //         final var hue = (_rainbowFirstPixelHue + (ledIndex * 180 / _ledBuffer.getLength())) % 180;

    //         _ledBuffer.setHSV(ledIndex, hue, 128, brightness);
    //     }

    //     // // Increase to make the rainbow "move", wrap it back to the front
    //     _rainbowFirstPixelHue += 3;
    //     _rainbowFirstPixelHue %= 180;
    // }

    private void fillBuffer() {
        Color detectedColor = m_colorSensor.getColor();
       for(var ledIndex = 0; ledIndex <  _ledBuffer.getLength(); ledIndex++) {
            _ledBuffer.setLED(ledIndex, detectedColor);
       }
        

        /**
         * Run the color match algorithm on our detected color
         */
        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
        colorString = "Blue";
        } else if (match.color == kRedTarget) {
        colorString = "Red";
        } else if (match.color == kGreenTarget) {
        colorString = "Green";
        } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
        } else {
        colorString = "Unknown";
        }

        /**
         * Open Smart Dashboard or Shuffleboard to see the color detected by the 
         * sensor.
         */
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }
}