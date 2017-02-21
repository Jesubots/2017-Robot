// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterGo extends Command {

    public ShooterGo() {

        requires(Robot.flyWheelShootBall);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = OI.getFlyWheelSpeed(); // 3250;
    	Robot.flyWheelShootBall.setRightShooterSpeed(speed);
    	
    	SmartDashboard.putNumber("Shooter - Speed",(double) Robot.flyWheelShootBall.getRightShooterSpeed());
    	SmartDashboard.putNumber("Shooter - Position", (double) Robot.flyWheelShootBall.getRightShooterPosition());
    }

    protected boolean isFinished() {
        return true;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
