package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MainHardware {
    /* Public OpMode members. */
    public DcMotor b_leftMotor = null;
    public DcMotor b_rightMotor = null;
    public DcMotor f_leftMotor = null;
    public DcMotor f_rightMotor = null;


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
        f_leftMotor   = hwMap.dcMotor.get("front left motor");
        b_leftMotor   = hwMap.dcMotor.get("back left motor");
        f_rightMotor  = hwMap.dcMotor.get("front right motor");
        b_rightMotor  = hwMap.dcMotor.get("back right motor");

        f_leftMotor.setDirection(DcMotor.Direction.REVERSE);
        b_leftMotor.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        f_leftMotor.setPower(0);
        b_leftMotor.setPower(0);
        f_rightMotor.setPower(0);
        b_rightMotor.setPower(0);

        // May want to use RUN_USING_ENCODERS if encoders are installed.
        f_leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        b_leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        f_rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        b_rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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