package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotTurn extends Command {

    public PivotTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.pivotTurnPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setInterruptible(true);
    	Robot.driveTrain.PivotTurnPIDInit(OI.getDriveMag(), OI.getPivotTurnDegree());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveTrain.PivotTurnIsFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.PivotTurnPIDStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.PivotTurnPIDStop();
    }
}
