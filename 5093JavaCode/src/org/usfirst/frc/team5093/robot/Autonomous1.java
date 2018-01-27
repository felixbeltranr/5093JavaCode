package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomous1 extends Command {
	private DifferentialDrive m_robotDrive
	 = new DifferentialDrive(new Spark(0), new Spark(1));
	private Timer m_timer = new Timer();
	
	public void initialize() {
		
		m_timer.reset();
		m_timer.start();

	}
	
	public void execute() {
		if (m_timer.get() < 3.0) {
			m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
		} else {
			m_robotDrive.stopMotor(); // stop robot
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return false;
	}

}
