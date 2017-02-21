package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.Robot;
import org.usfirst.frc.team5809.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PivotTurnPID extends PIDSubsystem {
	
	
	

	public PivotTurnPID() {    
		super("PivotTurnPID",RobotMap.PivotTurnPIDMap.kP, RobotMap.PivotTurnPIDMap.kI,
				RobotMap.PivotTurnPIDMap.kD, RobotMap.PivotTurnPIDMap.kF);
		// TODO Auto-generated constructor stub
        setInputRange(-180.0f,  180.0f);
        setOutputRange(-1.0, 1.0);
       // setAbsoluteTolerance(RobotMap.PivotTurnPIDMap.kToleranceDegrees);
        getPIDController().setContinuous(true);
	}

	public void initDefaultCommand() {
	}
	/*
	public void pidWrite(double output) {
	    Robot.driveTrain.setRotateToAngleRate(output); // rotateToAngleRate = output;
	}
*/
	protected double returnPIDInput(){
	    System.out.println("PivotTurnPID.returnPIDInput.Navx Angle " + 
	                               getPIDController().getSetpoint() + 
	                               " - " + Robot.driveTrain.ahrs.getYaw());
		return (getPIDController().getSetpoint() -  Robot.driveTrain.ahrs.getYaw());
	}

	protected void usePIDOutput(double output){

		//
		 System.out.println("PivotTurnPID::UsePIDOutput::");
		 System.out.print  ("   Enabled = " + getPIDController().isEnabled());
		 
		 System.out.print  ("   f_magnitude = " + Robot.driveTrain.f_magnitude);
		 System.out.println("   output = " + output);

		 Robot.driveTrain.DriveByValues(-output, output);
	}
	
	public void setPercentTolerance(double percentage){
		
	}
	
	
}
