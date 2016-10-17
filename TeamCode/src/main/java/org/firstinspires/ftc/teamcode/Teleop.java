package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main: Telop ", group="Main")
//@Disabled
public class Teleop extends LinearOpMode {

    /* Declare OpMode members. */
    MainHardware robot = new MainHardware();

    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;

        robot.init(hardwareMap);

        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            if(gamepad1.right_bumper){
                left /= 2;
                right /= 2;
            }

            robot.f_rightMotor.setPower(right);
            robot.b_rightMotor.setPower(right);
            robot.f_leftMotor.setPower(left);
            robot.b_leftMotor.setPower(left);

            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}
