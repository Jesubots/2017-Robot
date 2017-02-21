
package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.RobotMap;
import org.usfirst.frc.team5809.robot.commands.DriveManually;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

//import org.usfirst.frc.team5809.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	CANTalon leftBack = new CANTalon(RobotMap.backLeftCAN);
	CANTalon rightBack = new CANTalon(RobotMap.backRightCAN);
	CANTalon rightFront = new CANTalon(RobotMap.frontRightCAN);
	CANTalon leftFront = new CANTalon(RobotMap.frontLeftCAN);
	
	
	AHRS ahrs;
	DriveStraightPID  driveStraightPID;
	PivotTurnPID  pivotTurnPID;
	EdgeTurnPID  edgeTurnPID;
	
	
	double f_magnitude;
	double rotateToAngleRate;
	private static DriveTrain instance;


	RobotDrive robotDrive = new RobotDrive(leftFront, rightFront);
	
	
	public DriveTrain(){
	
		leftFront.changeControlMode(TalonControlMode.PercentVbus);
		leftFront.set(0);
		rightFront.changeControlMode(TalonControlMode.PercentVbus);
		rightFront.set(0);

		leftBack.changeControlMode(TalonControlMode.Follower);
		leftBack.set(leftFront.getDeviceID());		
		
		rightBack.changeControlMode(TalonControlMode.Follower);
		rightBack.set(rightFront.getDeviceID());
		
		leftFront.setInverted(true);
		rightFront.setInverted(true);
		leftBack.setInverted(true);
		rightBack.setInverted(true);
		
		setCoast();
		
		/*
		leftFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		
		leftFront.setPosition(0);
		rightFront.setPosition(0);
		*/
		
		{
		
		
	  try {
          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
          ahrs = new AHRS(SPI.Port.kMXP); 
          //ahrs = new AHRS(Port.kUSB); 
      } catch (RuntimeException ex ) {
          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
      }
      driveStraightPID = new  DriveStraightPID();
     pivotTurnPID = new PivotTurnPID();

      /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
      /* tuning of the Turn Controller's P, I and D coefficients.            */
      /* Typically, only the P value needs to be modified.                   */
      LiveWindow.addActuator("DriveSystem", "RotateController", driveStraightPID.getPIDController());
}
		
		

	
	//public RobotDrive(int frontLeftMotor,
    //        int rearLeftMotor,
    //        int frontRightMotor,
    //        int rearRightMotor)
	
	 
	}
	 
	//public RobotDrive(int leftMotorChannel,
    //        int rightMotorChannel)
	// CAN(1) is left   CAN(4) is right
	//RobotDrive robotDrive = new RobotDrive(leftBack, rightBack);
		
		
	public static DriveTrain getInstance() {
		if (instance == null) {
			
		instance = new DriveTrain();
		
		}
		
	return instance;
	
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
		setDefaultCommand(new DriveManually());
    }
    
   public void ManualDrive(Joystick stick){
    	robotDrive.tankDrive(stick.getY(), stick.getThrottle(),true);
    }
    
   public void Stop(){
    	robotDrive.stopMotor();
    }
    	
   public void DriveByValues(double left, double right){
	   robotDrive.tankDrive(left, right);
   }
   
   public void DriveByAngleValues(double dMag, double dRotateMag){
	   // public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
	  robotDrive.arcadeDrive(dMag, dRotateMag, false);
   }
 /*
   public void pidWrite(double output) {
	      rotateToAngleRate = output;
	  }
   
	public void UsePIDOutput(double output){

		//
		 System.out.println("DriveMotorSubsystem::UsePIDOutput");
		 System.out.println("Enabled = " + PIDController.isEnabled());
		 System.out.println("f_magnitude = " + Robot.driveTrain.f_magnitude);
		 System.out.println("output = " + output);

		 robotDrive.arcadeDrive(Robot.driveTrain.f_magnitude, output, false);
	}
*/  
	public void DriveStraightPIDInit(double dMag, double dDeg){
		ahrs.zeroYaw();
		System.out.println("DriveMotorSubsystem.PIDInit");
		f_magnitude = -dMag;
		driveStraightPID.setSetpoint(dDeg);
		driveStraightPID.enable();	
		 System.out.println("Enabled = " + driveStraightPID.getPIDController().isEnabled());
		}

	public void DriveStraightPIDStop(){
		System.out.println("Enabled = " + driveStraightPID.getPIDController().isEnabled());
		System.out.println("DriveMotorSubsystem.PIDStop");
		driveStraightPID.getPIDController().reset();
		robotDrive.stopMotor();
	}
	
	public void PivotTurnPIDInit(double dMag, double targetDegree){
		System.out.println("DriveMotorSubsystem.PivotTurnPIDInit");
		f_magnitude = dMag;
		pivotTurnPID.setSetpoint(targetDegree);
		ahrs.zeroYaw();
		pivotTurnPID.enable();	
		pivotTurnPID.setPercentTolerance(RobotMap.tolerancePercent);
		pivotTurnPID.setAbsoluteTolerance(1.0);
		 System.out.println("Enabled = " + pivotTurnPID.getPIDController().isEnabled());
		}


	public void PivotTurnPIDStop(){
		 System.out.println("Enabled = " + pivotTurnPID.getPIDController().isEnabled());
		System.out.println("DriveMotorSubsystem.PivotTurnPIDStop");
		pivotTurnPID.getPIDController().reset();
		robotDrive.stopMotor();
	}
	
	public boolean PivotTurnIsFinished(){
		return pivotTurnPID.onTarget();
	}
    
	public void EdgeTurnPIDInit(double dMag, double targetDegree){
		System.out.println("DriveMotorSubsystem.EdgeTurnPIDInit");
		f_magnitude = dMag;
		edgeTurnPID.setSetpoint(targetDegree);
		ahrs.zeroYaw();
		
		edgeTurnPID.enable();	
		edgeTurnPID.setPercentTolerance(RobotMap.tolerancePercent);
		pivotTurnPID.setAbsoluteTolerance(1.0);
		 System.out.println("Enabled = " + edgeTurnPID.getPIDController().isEnabled());
		}


	public void EdgeTurnPIDStop(){
		 System.out.println("Enabled = " + edgeTurnPID.getPIDController().isEnabled());
		System.out.println("DriveMotorSubsystem.EdgeTurnPIDStop");
		edgeTurnPID.getPIDController().reset();
		robotDrive.stopMotor();
	}
	
	public boolean EdgeTurnIsFinished(){
		return edgeTurnPID.onTarget();
	}
	
	
	public AHRS getAhrs() {
		return ahrs;
	}

	public void setAhrs(AHRS ahrs) {
		this.ahrs = ahrs;
	}

	public double getRotateToAngleRate() {
		return rotateToAngleRate;
	}

	public void setRotateToAngleRate(double rotateToAngleRate) {
		this.rotateToAngleRate = rotateToAngleRate;
	}

	public double getF_magnitude() {
		return f_magnitude;
	}

	public void setF_magnitude(double f_magnitude) {
		this.f_magnitude = f_magnitude;
	}
 
	public void setBrake() {
		leftFront.enableBrakeMode(true);
		rightFront.enableBrakeMode(true);
		leftBack.enableBrakeMode(true);
		rightBack.enableBrakeMode(true);
	}

	public void setCoast() {
		leftFront.enableBrakeMode(false);
		rightFront.enableBrakeMode(false);
		leftBack.enableBrakeMode(false);
		rightBack.enableBrakeMode(false);
	}
}
