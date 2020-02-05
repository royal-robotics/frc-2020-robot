package frc.libs.controls;

import edu.wpi.first.wpilibj.*;
import static frc.libs.controls.Controllers.*;

public class Button implements IButton {
    protected final ButtonType type;
    protected final Joystick stick;
    protected final Logitech310Button buttonId;
    protected boolean previousValue;

    /***
     * Create a standard Joystick button instance.
     *
     * @param stick 	The Joystick that the button is on.
     * @param buttonId	The Id/Index of the button.
     * @param type		The type of button to create.
     */
    public Button(Joystick stick, Logitech310Button buttonId, ButtonType type)
    {
        this.stick = stick;
        this.buttonId = buttonId;
        this.type = type;
        this.previousValue = false;
    }

    /***
     * Get the position of the button.
     *
     * @return true if the button is pressed and false otherwise.
     */
    protected synchronized boolean getPosition()
    {
        if (this.buttonId.isPOV)
            return this.stick.getPOV() == this.buttonId.id;
        else
            return this.stick.getRawButton(this.buttonId.id);
    }

    /***
     * Get the button isPressed state for this type of button.
     *
     * @return	true if button action should happen, false otherwise.
     */
    public synchronized boolean isPressed()
    {
        boolean value = this.getPosition();
        boolean retValue = false;

        switch(this.type)
        {
            case Hold:
            {
                retValue = value;
                break;
            }
            case Toggle:
            {
                retValue = !this.previousValue && value;
                break;
            }
        }

        this.previousValue = value;

        return retValue;
    }
}
