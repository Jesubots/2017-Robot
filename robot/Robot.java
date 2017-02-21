
package org.usfirst.frc.team5809.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team5809.robot.commands.DriveSlow;
import org.usfirst.frc.team5809.robot.commands.DriveStraight;
import org.usfirst.frc.team5809.robot.commands.DriveTimed;
import org.usfirst.frc.team5809.robot.commands.EdgeTurn;
import org.usfirst.frc.team5809.robot.commands.PivotTurn;
import org.usfirst.frc.team5809.robot.commands.ShooterStop;
import org.usfirst.frc.team5809.robot.commands.DoNothing;
import org.usfirst.frc.team5809.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5809.robot.subsystems.EdgeTurnPID;
import org.usfirst.frc.team5809.robot.subsystems.PivotTurnPID;
import org.usfirst.frc.team5809.robot.subsystems.Pneumatics;
import org.usfirst.frc.team5809.robot.subsystems.WinchClimb;
import org.usfirst.frc.team5809.robot.subsystems.FlyWheelShootBall;
import org.usfirst.frc.team5809.robot.subsystems.Gears;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = DriveTrain.getInstance();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final PivotTurnPID  pivotTurnPID = new PivotTurnPID();
	public static final EdgeTurnPID  edgeTurnTurnPID = new EdgeTurnPID();
	public static final FlyWheelShootBall flyWheelShootBall = FlyWheelShootBall.getInstance();
	public static final WinchClimb winchClimb = new WinchClimb();
	public static final Gears  gears = new Gears();
	//public static final Sonar sonar = new Sonar();
	//public static Camera camera = new Camera();
	
	
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new DoNothing());
        chooser.addObject("My Auto", new DriveSlow());
        chooser.addObject("Timed Drive", new DriveTimed());
        chooser.addObject("Drive Straight", new DriveStraight());
        chooser.addObject("Pivot Turn", new PivotTurn());
        chooser.addObject("Edge Turn", new EdgeTurn());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Drive Time", RobotMap.defaultDriveTimed);
        SmartDashboard.putNumber("Drive Magnitude", RobotMap.defaultDriveMag);
        SmartDashboard.putNumber("Pivot Turn", RobotMap.defaultPivotTurn);
        SmartDashboard.putNumber("Edge Turn", RobotMap.defaultEdgeTurn);
        SmartDashboard.putNumber("Flywheel Power", RobotMap.defaultFlyWheelPower);
        SmartDashboard.putNumber("Winch Power", RobotMap.deaultWinchPower);
        

        
//        UsbCamera c1 = new UsbCamera()
//        CameraServer camServer = CameraServer.getInstance();
        //CameraServer.getInstance().addServer(0);
 //       VideoSource v2 = new VideoSource("cam1");
 //       CameraServer.getInstance().startAutomaticCapture();
       // CameraServer.getInstance().add;
//        CameraServer.getInstance().startAutomaticCapture("cam1");
        //camServer.setQuality(50);
        //camServer.startAutomaticCapture("cam0");
        //server.startAutomaticCapture("cam1");
        
        
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	Scheduler.getInstance().add(new ShooterStop());

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        
        oi.setDriveTime(SmartDashboard.getNumber("Drive Time",0.0));
        oi.setDriveMagnitude(SmartDashboard.getNumber("Drive Magnitude",0.0));
        oi.setPivotTurnDegree(SmartDashboard.getNumber("Pivot Turn",0.0));
        oi.setEdgeTurnDegree(SmartDashboard.getNumber("Edge Turn",0.0));
        oi.setFlyWheelPower(SmartDashboard.getNumber("Flywheel Power", 0.0));
        oi.setWinchPower(SmartDashboard.getNumber("Winch Power", 0.0));

        
        
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        oi.setDriveTime(SmartDashboard.getNumber("Drive Time",0.0));
        oi.setDriveMagnitude(SmartDashboard.getNumber("Drive Magnitude",0.0));
        oi.setPivotTurnDegree(SmartDashboard.getNumber("Pivot Turn",0.0));
        oi.setEdgeTurnDegree(SmartDashboard.getNumber("Edge Turn",0.0));
        oi.setFlyWheelPower(SmartDashboard.getNumber("Flywheel Power", 0.0));
        oi.setWinchPower(SmartDashboard.getNumber("Winch Power", 0.0));
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
