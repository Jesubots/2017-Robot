package org.usfirst.frc.team5809.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5809.robot.commands.ClimbRope;
import org.usfirst.frc.team5809.robot.commands.DriveSlow;
import org.usfirst.frc.team5809.robot.commands.DriveStraightHighGear;
import org.usfirst.frc.team5809.robot.commands.DriveStraightLowGear;
import org.usfirst.frc.team5809.robot.commands.HighGear;
import org.usfirst.frc.team5809.robot.commands.LowGear;
import org.usfirst.frc.team5809.robot.commands.ReleaseGear;
import org.usfirst.frc.team5809.robot.commands.ShiftOff;
import org.usfirst.frc.team5809.robot.commands.ShooterGo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
     public static Joystick stick = new Joystick(0);
     public static Button xButton = new JoystickButton(stick, 1);
     public static Button aButton = new JoystickButton(stick, 2);
     public static Button leftBumper = new JoystickButton(stick, 5);
     public static Button rightBumper = new JoystickButton(stick, 6);
     public static Button yButton = new JoystickButton(stick, 4);    
     public static Button bButton = new JoystickButton(stick, 3);
     public static Button leftTrigger = new JoystickButton(stick, 7);
     public static Button rightTrigger = new JoystickButton(stick, 8);



     private static double driveTime;
     private static double driveMag;
     private static double pivotTurnDegree;
     private static double edgeTurnDegree;
     private static double flyWheelSpeed;
     private static double winchPower;
     
    public static double getWinchPower() {
 		return winchPower;
 	}

 	public void setWinchPower(double winchPower) {
 		OI.winchPower = winchPower;
 	}
     
     public static double getFlyWheelSpeed() {
			return flyWheelSpeed;
		}

	public void setFlyWheelPower(double flyWheelPower) {
			OI.flyWheelSpeed = flyWheelPower;
	}
     
     public static double getEdgeTurnDegree() {
		return edgeTurnDegree;
	}

	public void setEdgeTurnDegree(double edgeTurnDegree) {
		OI.edgeTurnDegree = edgeTurnDegree;
	}

	public static double getPivotTurnDegree() {
		return pivotTurnDegree;
	}

	public void setPivotTurnDegree(double pivotTurnDegree) {
		OI.pivotTurnDegree = pivotTurnDegree;
	}

	public static double getDriveMag() {
		return driveMag;
	}

	public void setDriveMagnitude(double driveMag) {
		OI.driveMag = driveMag;
	}

	public static double getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(double driveTime) {
		OI.driveTime = driveTime;
	}

	public OI() {
    	 initButtons();
     }
     
     private void initButtons(){
       	 aButton.whileHeld(new DriveSlow());
       	 yButton.whenPressed(new DriveStraightLowGear());
       	 bButton.whenPressed(new DriveStraightHighGear());
       	 xButton.whenPressed(new ReleaseGear());
    	 
    	 leftBumper.whenPressed(new HighGear());
    	 rightBumper.whenPressed(new LowGear());
    	 
    	 leftBumper.whenReleased(new ShiftOff());
    	 rightBumper.whenReleased(new ShiftOff());
    	 
     	 //rightTrigger.whileHeld(new ShootFlyWheel());
     	 rightTrigger.whileHeld(new ShooterGo());
     	 leftTrigger.whileHeld(new ClimbRope());

     }
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
     
     
      
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
      
      public Joystick getDriveStick(){
    	    return stick;
    	}

	
      
}

