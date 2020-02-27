{
    # Important! Before generating the project in the frc-characterization tool remember to:
    #   - Set the team number to "2522"
    #   - Set the project type to "SparkMax (Brushless/Neo)"

    # Ports for the spark motor controllers
    "leftMotorPorts": [3, 4],
    "rightMotorPorts": [1, 2],

    # Inversions of the motors
    # Note: Inversions of the slaves (i.e. any motor *after* the first on
    # each side of the drive) are *with respect to their master*.  This is
    # different from the other poject types!
    "leftMotorsInverted": [True, False],
    "rightMotorsInverted": [False, False],

    # The total gear reduction between the motor and the wheels, expressed as
    # a fraction [motor turns]/[wheel turns]
    "gearing": 9.36,

    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 6,

    # Your gyro type (one of "NavX", "Pigeon", "ADXRS450", "AnalogGyro", or "None")
    "gyroType": "ADXRS450",

    # Whatever you put into the constructor of your gyro
    # Could be:
    # "SPI.Port.kMXP" (MXP SPI port for NavX or ADXRS450),
    # "SerialPort.Port.kMXP" (MXP Serial port for NavX),
    # "I2C.Port.kOnboard" (Onboard I2C port for NavX),
    # "0" (Pigeon CAN ID or AnalogGyro channel),
    # "new WPI_TalonSRX(3)" (Pigeon on a Talon SRX),
    # "" (NavX using default SPI, ADXRS450 using onboard CS0, or no gyro)
    "gyroPort": "",
}


