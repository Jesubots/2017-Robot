// Strong Hold BOB 2016 Commands (Shooter Stop)

package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterStop extends Command {

    public ShooterStop() {

        requires(Robot.flyWheelShootBall);

    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	Robot.flyWheelShootBall.setRightShooterStop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
