package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MainHardware {
    /* Public OpMode members. */
    public DcMotor leftMotors = null;
    public DcMotor rightMotors = null;
    public DcMotor beaterBar = null;
    public DcMotor launcher = null;

    public Servo leftButton = null;
    public Servo rightButton = null;
    public Servo launch = null;
    public Servo phone = null;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public MainHardware() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotors  = hwMap.dcMotor.get("left motors");
        rightMotors = hwMap.dcMotor.get("right motors");
        beaterBar = hwMap.dcMotor.get("beater bar");
        launcher = hwMap.dcMotor.get("launcher");

        leftButton = hwMap.servo.get("leftButton");
        rightButton = hwMap.servo.get("rightButton");
        phone = hwMap.servo.get("phone");
        launch = hwMap.servo.get("launch");

        leftMotors.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        leftMotors.setPower(0);
        rightMotors.setPower(0);
        beaterBar.setPower(0);
        launcher.setPower(0);

        leftButton.setPosition(0.35);
        rightButton.setPosition(0.9);
        launch.setPosition(0);//.5
        phone.setPosition(1); //.5 up

        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        b_leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        b_rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     **/

    public void waitForTick(long periodMs)  throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}