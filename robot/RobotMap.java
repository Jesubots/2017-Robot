package org.usfirst.frc.team5809.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    	public static int frontLeftCAN = 1;
    	public static int frontRightCAN = 4;
    	public static int backLeftCAN = 0;
    	public static int backRightCAN = 3;
    	public static  int flyWheelCAN = 2;
    	public static int winchPWM = 0;
    	public static int leftGearPWM = 1;
    	public static int rightGearPWM = 0;
    	public static double tolerancePercent = 2;
    	
    	public static double defaultDriveTimed = .5;
    	public static double defaultDriveMag = .25;
    	public static double defaultPivotTurn = 30;
    	public static int compressorPort = 0;
    	public static int lowGearPort = 0;
    	public static int highGearPort = 1;
    	public static double gearShiftDelay = .5;
		public static double defaultEdgeTurn = 30;
		public static double defaultFlyWheelPower = 5000.0;  //.5;
		public static double deaultWinchPower = .5;
    	

		public class DriveStraightPIDMap {
    	public static final double kP = 0.03;
    	public static final double kI = 0.00;
    	public static final double kD = 0.00;
    	public static final double kF = 0.00;
    	public static final double kToleranceDegrees = 2.0f;
    	}
    	
    	public class PivotTurnPIDMap {
        	public static final double kP = 0.03;
        	public static final double kI = 0.00;
        	public static final double kD = 0.00;
        	public static final double kF = 0.00;
        	public static final double kToleranceDegrees = 2.0f;
        	}
    	
    	public class EdgeTurnPIDMap {
        	public static final double kP = 0.03;
        	public static final double kI = 0.00;
        	public static final double kD = 0.00;
        	public static final double kF = 0.00;
        	public static final double kToleranceDegrees = 2.0f;
        	}
    	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
