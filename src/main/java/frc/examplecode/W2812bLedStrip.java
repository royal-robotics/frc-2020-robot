package frc.examplecode;

import edu.wpi.first.wpilibj.*;

/// <remark>There can only be one pwm port configured as an AddressableLed</remark>
public class W2812bLedStrip {

    private final AddressableLED _ledStrip;
    private final AddressableLEDBuffer _ledBuffer;
    private final Joystick _controller;
    private final Notifier _controlLoop;

    private int _rainbowFirstPixelHue = 0;

    public W2812bLedStrip(Joystick controller, int pwmPort) {
        _ledStrip = new AddressableLED(pwmPort);
        _ledBuffer = new AddressableLEDBuffer(150);
        _ledStrip.setLength(_ledBuffer.getLength());

        _controller = controller;
        _controlLoop = initializeControlLoop();
    }

    private Notifier initializeControlLoop() {
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
        _ledStrip.setData(_ledBuffer);
    }

    /// <summary>
    /// Fills the buffer with a rainbow pattern that rotates each time the buffer is filled.
    /// </summary>
    private void fillBuffer() {
        // Pressing the right trigger lowers the saturation
        var saturation = (int)(255.0 - (_controller.getRawAxis(3) * 255.0));

        for (var ledIndex = 0; ledIndex < _ledBuffer.getLength(); ledIndex++) {
            // Hue used because its a linear representation of color spectrum
            final var hue = (_rainbowFirstPixelHue + (ledIndex * 180 / _ledBuffer.getLength())) % 180;

            _ledBuffer.setHSV(ledIndex, hue, saturation, 128);
        }

        // Increase to make the rainbow "move", wrap it back to the front
        _rainbowFirstPixelHue += 3;
        _rainbowFirstPixelHue %= 180;
    }
}