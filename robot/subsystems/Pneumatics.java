package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

	

public class Pneumatics extends Subsystem {
	
	Compressor gearShift = new Compressor(RobotMap.compressorPort);
	DoubleSolenoid shiftDouble = new DoubleSolenoid(RobotMap.lowGearPort,RobotMap.highGearPort);
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }


    public void shift2(){
    	gearShift.setClosedLoopControl(true);
    	gearShift.start();
    	shiftDouble.set(DoubleSolenoid.Value.kReverse);
    } 
    
    
    public void shift(){
    	gearShift.setClosedLoopControl(true);
    	gearShift.start();
    	shiftDouble.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftOff(){
    	shiftDouble.set(DoubleSolenoid.Value.kOff);
    }
}
