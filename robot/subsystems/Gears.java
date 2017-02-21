package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gears extends Subsystem {
	
	Victor rightGearPWM = new Victor(RobotMap.rightGearPWM);
	Victor leftGearPWM = new Victor(RobotMap.leftGearPWM);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void DropGear(){
    	rightGearPWM.set(.5);
    	leftGearPWM.set(.5);
    }
}

