// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class MaintainSpeed extends Command {

    public MaintainSpeed() {

        requires(Robot.flyWheelShootBall);

    }

    protected void initialize() {
    }

    protected void execute() {
    	double speed = Robot.flyWheelShootBall.getRightShooterSetpoint();
    	Robot.flyWheelShootBall.setRightShooterSpeed(speed);
    	
    }

    protected boolean isFinished() {
        return false;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
