package org.usfirst.frc.team5809.robot.subsystems;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.RobotMap;
import org.usfirst.frc.team5809.robot.commands.MaintainSpeed;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlyWheelShootBall extends Subsystem {
	private static volatile FlyWheelShootBall instance = null ;
	
//	private FlyWheelShootBall() { 
	        // hidden constructor
//	}    

	public static FlyWheelShootBall getInstance() {
	        if (instance == null) {
	            synchronized(FlyWheelShootBall.class) {
	                if (instance == null) {
	                    instance = new FlyWheelShootBall();
	                }
	            }
	        }
	        return instance;
	}

	public CANTalon rightShooter = new CANTalon(RobotMap.flyWheelCAN);
	
	private double speed = 5000.0;

	// ---- used IN PID TUNING----//
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;

	// ---------------------------//

	private FlyWheelShootBall() {

		rightShooter.changeControlMode(TalonControlMode.Speed);
		rightShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightShooter.reverseSensor(false);
		rightShooter.reverseOutput(true);
		// when installed check to make sure encoder and voltage are matched
		rightShooter.configNominalOutputVoltage(+0.0f, +0.0f);
		rightShooter.configPeakOutputVoltage(+0.0f, -12.0f);
		//rightShooter.setProfile(0);
		
		double rightF = 0.02725;
		double rightP = 0.11;
		double rightI = 0.0011;
		double rightD = 1.1;
		
		int rightIZone = 50;
		
		rightShooter.setPID(rightP, rightI, rightD, rightF, rightIZone, rightShooter.getCloseLoopRampRate(), 0);
		
		/*
		rightShooter.setF(0.02997); // was 0.02997 before weights //0.0611 during burnout testing
		rightShooter.setP(0.11);//0.11 before weights //1.166 during burnout
		rightShooter.setI(.0011);// .0011-original//
		rightShooter.setD(1.1);
		rightShooter.setIZone(50);// might be increased to 100 (Derrick
									// -1/29/16)
		*/
		

	}

	public void stop() {
		rightShooter.set(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MaintainSpeed());
		//setDefaultCommand(new ShooterPIDTest());
	}

	public void setRightShooterSpeed(double speed) {
		rightShooter.set(speed);
	}


	public void setRightShooterStop() {
		rightShooter.set(0);
	}



	public double getRightShooterSpeed() {
		return rightShooter.getSpeed();
	}


	public double getRightShooterSetpoint() {
		return rightShooter.getSetpoint();
	}

	public double rightMotorOutputVoltage() {
		return rightShooter.getOutputVoltage();
	}

	public double rightMotorBusVoltage() {
		return rightShooter.getBusVoltage();
	}
		
	// ---------TUNING PID SHOOTER MOTORS----USED FOR GETTING SHOOTERS TO BE
	// CONSISTENT-----//
	public void rightShooterpIDTest() {
		double motorOutput = rightShooter.getOutputVoltage()
				/ rightShooter.getBusVoltage();
		double motorSpeed = rightShooter.getSpeed();

		// OPEN THE CONSOLE FROM THE DRIVER STATION TO READ OUT PUT VALUES
		_sb.append("\tout:");
		_sb.append(motorOutput);
		_sb.append("\tspd:");
		_sb.append(motorSpeed);

		// --IF YOU PUSH A BCUTTON THE SHOOTER WILL ATTEMPT TO GET TO
		// "Right Fixed Target Speed"
		if (true) { // Robot.oi.operatorController.getRawButton(2)) {
 
			/* Speed mode */

			double rightFixedTargetSpeed = OI.getFlyWheelSpeed(); // 4000
			// double targetSpeed = Robot.oi.xBoxController.getRawAxis(0) *
			// 1500.0;
			rightShooter.changeControlMode(TalonControlMode.Speed);
			rightShooter.set(rightFixedTargetSpeed);

			_sb.append("\terr:");
			_sb.append(rightShooter.getClosedLoopError());
			_sb.append("\ttrg:");
			_sb.append(rightFixedTargetSpeed);
		}// OTHERWISE IT WILL DRIVE THE MOTOR OFF THE LEFT JOYSTICK X-AXIS
		else {
			rightShooter.changeControlMode(TalonControlMode.PercentVbus);
		//	rightShooter.set(Robot.oi.operatorController.getRawAxis(5));
		}

		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());

		}

		_sb.setLength(0);

	}


	// /----THIS IS NOT WHERE YOU CHANGE THE SHOOTER SPEED

	public boolean isShooterAtSpeed() {
		double rightSetPoint = rightShooter.getSetpoint();
		double rightSpeed = rightShooter.getSpeed();
		double shootingErrorThreshold = 50;
		/*
		System.out.println("leftSetPoint: " + leftSetPoint);
		System.out.println("leftSpeed: " + leftSpeed);
		System.out.println("leftError: " + leftShooter.getClosedLoopError());
		
		System.out.println("rightSetPoint: " + rightSetPoint);
		System.out.println("rightSpeed: " + rightSpeed);
		System.out.println("rightError: " + rightShooter.getClosedLoopError());
		*/
		if (Math.abs(rightSpeed - rightSetPoint) < shootingErrorThreshold) {
			return true;
		} else
			return false;
	}
	
	public boolean hasShooterReachedSetpoint(){
		double rightSetPoint = rightShooter.getSetpoint();
		double rightSpeed = rightShooter.getSpeed();
		
		return (rightSpeed >= rightSetPoint) ;
	}
	

	public double getRightShooterPosition(){
		return rightShooter.getEncPosition();
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}

