// Strong Hold BOB 2016 Commands (Shooter Go)

package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class ShooterGoAuto extends Command {

    public ShooterGoAuto() {

        requires(Robot.flyWheelShootBall);

    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	Robot.flyWheelShootBall.setRightShooterSpeed(OI.getFlyWheelSpeed());
    	
    }

    protected boolean isFinished() {
        return true;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
