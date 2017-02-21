package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchClimb extends Subsystem {
	
	public VictorSP winchMotor = new VictorSP(RobotMap.winchPWM);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void StartWinch(){
    	
    	winchMotor.set(OI.getWinchPower()*-1);
    	
    }
    
    public void StopWinch(){
    	
    	winchMotor.set(0);
    	
    }
}

