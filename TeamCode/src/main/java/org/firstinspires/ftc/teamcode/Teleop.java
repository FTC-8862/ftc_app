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
        double left, right, beaterBar, launcher;
        boolean leftButton = true, rightButton = true, launch = false;
        beaterBar = 0;
        launcher = 0;
        double t  = time;

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            if(gamepad1.right_bumper){
                left /= 2;
                right /= 2;
            }


            if(beaterBar != 0 && (gamepad1.dpad_down || gamepad1.dpad_up)){
                beaterBar = 0;
            }else if(gamepad1.dpad_down){
                beaterBar = 1;
            }else if(gamepad1.dpad_up) {
                beaterBar = -1;
            }

            launcher = gamepad2.left_trigger >= 0.1 ? 1:0;


            if(gamepad2.right_bumper && time-t>=.1){
                rightButton = !rightButton;
                t = time;
            }
            if(gamepad2.left_bumper && time-t>=.1){
                leftButton = !leftButton;
                t = time;
            }


            if(gamepad2.dpad_up) {
                launch = true;
            }else if (gamepad2.dpad_down) {
                launch = false;
            }

            if(leftButton){
                robot.leftButton.setPosition(0.35);
            }else{
                robot.leftButton.setPosition(0.8);
            }

            if(rightButton){
                robot.rightButton.setPosition(0.9);
            }else{
                robot.rightButton.setPosition(0.45);
            }

            if(launch){
                robot.launch.setPosition(0.5);
            }else{
                robot.launch.setPosition(0.0);
            }

            robot.rightMotors.setPower(right);
            robot.leftMotors.setPower(left);
            robot.beaterBar.setPower(beaterBar);
            robot.launcher.setPower(launcher);
            telemetry.addData("phone", "%.2f", robot.phone.getPosition());
            telemetry.addData("launch", "%.2f", robot.launch.getPosition());
            telemetry.addData("left button", "%.2f", robot.rightButton.getPosition());
            telemetry.addData("right button", "%.2f", robot.leftButton.getPosition());
            telemetry.update();

            robot.waitForTick(40);
            idle();
        }
    }
}
