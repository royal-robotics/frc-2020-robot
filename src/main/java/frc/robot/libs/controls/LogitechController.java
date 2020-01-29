package frc.libs.controls;
import edu.wpi.first.wpilibj.Joystick;

public class LogitechController
{
    private final Joystick joystick;
    private final double deadband;

    public LogitechController(int port, double deadband)
    {
        joystick = new Joystick(port);
        this.deadband = deadband;
    }

    public double LXAxis()
    {
        double value = joystick.getRawAxis(0);

        if (value > deadband || value < -deadband)
        {
            return value;
        }

        return 0;
    }

    public double LYAxis()
    {
        double value = joystick.getRawAxis(1);
        
        if (value > deadband || value < -deadband)
        {
            return value;
        }

        return 0;
    }

    public double LTrigger()
    {
        double value = joystick.getRawAxis(2);

        if (value > deadband)
        {
            return value;
        }

        return 0;
    }

    public double RTrigger()
    {
        double value = joystick.getRawAxis(3);

        if (value > deadband)
        {
            return value;
        }

        return 0;
    }
    
    public double RXAxis()
    {
        double value = joystick.getRawAxis(4);

        if (value > deadband)
        {
            return value;
        }

        return 0;
    }

    public double RYAxis()
    {
        double value = joystick.getRawAxis(5);

        if (value > deadband)
        {
            return value;
        }

        return 0;
    }

    public boolean ButtonA()
    {
        return joystick.getRawButton(1);
    }

    public boolean ButtonB()
    {
        return joystick.getRawButton(2);
    }

    public boolean ButtonX()
    {
        return joystick.getRawButton(3);
    }

    public boolean ButtonY()
    {
        return joystick.getRawButton(4);
    }
    
    public boolean LBumper()
    {
        return joystick.getRawButton(5);
    }

    public boolean RBumper()
    {
        return joystick.getRawButton(6);
    }

    public boolean Back()
    {
        return joystick.getRawButton(7);
    }

    public boolean Start()
    {
        return joystick.getRawButton(8);
    }

    public boolean LStick()
    {
        return joystick.getRawButton(9);
    }

    public boolean RStick()
    {
        return joystick.getRawButton(10);
    }

    public boolean DUp()
    {
        return joystick.getPOV() == 0;
    }

    public boolean DUpRight()
    {
        return joystick.getPOV() == 45;
    }

    public boolean DRight()
    {
        return joystick.getPOV() == 90;
    }

    public boolean DDownRight()
    {
        return joystick.getPOV() == 135;
    }

    public boolean DDown()
    {
        return joystick.getPOV() == 180;
    }

    public boolean DDownLeft()
    {
        return joystick.getPOV() == 225;
    }

    public boolean DLeft()
    {
        return joystick.getPOV() == 270;
    }

    public boolean DUpLeft()
    {
        return joystick.getPOV() == 315;
    }
}