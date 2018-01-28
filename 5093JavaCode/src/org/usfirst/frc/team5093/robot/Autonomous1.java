package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomous1 extends Command {
	private DifferentialDrive m_robotDrive;
	private Timer m_timer = new Timer();
	boolean quiza;
	
	public Autonomous1(DifferentialDrive m_robotDrive) {
    	//requires(Robot.turret);
		this.m_robotDrive = m_robotDrive;
    }
	
	public void initialize() {
		
		m_timer.reset();
		m_timer.start();
		quiza = false;

	}
	
	public void execute() {
		if (m_timer.get() < 3.0) {
			m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
		} else {
			m_robotDrive.stopMotor(); // stop robot
			quiza = true;
		}
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return quiza;
	}
	
	// Called once after isFinished returns true
    /*protected void end() {
    	HoldAngle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }*/

}
