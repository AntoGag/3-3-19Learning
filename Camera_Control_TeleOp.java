package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Camera_Control", group="TeleOp")
public class Camera_Control_TeleOp extends LinearOpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    Servo Pan;
    Servo Tilt;

    double setpower = 1.0;
    double leftMotorPower = 1.0;
    double rightMotorPower = 1.0;

    ElapsedTime ResetTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initilizes the hardware

        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);

        FrontRight = hardwareMap.dcMotor.get("FR");
        FrontRight.setDirection(DcMotor.Direction.REVERSE);

        BackLeft = hardwareMap.dcMotor.get("BL");
        BackLeft.setDirection(DcMotor.Direction.FORWARD);

        BackRight = hardwareMap.dcMotor.get("BR");
        BackRight.setDirection(DcMotor.Direction.REVERSE);

        Pan = hardwareMap.servo.get("Pan");
        Tilt = hardwareMap.servo.get("Tilt");

        Tilt.setPosition(0.5);
        sleep(250); //Sleep intended to give servo time to initialize

        //End of Initialization

        waitForStart();

            while (opModeIsActive()) {

                //Speed Change
                if (gamepad1.a) {
                    setpower = 1.0;
                }

                if (gamepad1.b) {
                    setpower = 0.75;
                }

                if (gamepad1.x) {
                    setpower = 0.5;
                }

                if (gamepad1.y) {
                    setpower = 0.25;
                }

                //Driving Forward
                leftMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -setpower, setpower);
                rightMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -setpower, setpower);

                //Turning Left

                //Turning Right

                //Setting motor power to respective joystick input
                FrontLeft.setPower(leftMotorPower);
                FrontRight.setPower(rightMotorPower);
                BackLeft.setPower(leftMotorPower);
                BackRight.setPower(rightMotorPower);

                //Camera Control
                    //Pan
                    if (gamepad1.right_stick_x > .3) {
                        Pan.setPosition(1);
                    }

                    if (gamepad1.right_stick_x < -.3) {
                        Pan.setPosition(0);
                    }

                    if (gamepad1.right_stick_x < .3 && gamepad1.right_stick_x > -.3) {
                        Pan.setPosition(.5);
                    }

                    //Tilt

                    double joy = gamepad1.right_stick_y;
                    double TiltPos = joy/2 + 0.5;

                    Tilt.setPosition(TiltPos);

                        //Tilt Presets
                        if (gamepad1.dpad_down) {
                            Tilt.setPosition(TiltPos-.25);
                        }

                        if (gamepad1.dpad_up) {
                            Tilt.setPosition(TiltPos+.25);
                        }

                //Telemetry Data
                telemetry.addData("Current Speed", setpower);

            }
    }
}
