package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoTouchlessEncoder extends Command{

	Robot robotin;
	private Timer m_timer = new Timer();
	boolean quiza;
	
	public AutonomoTouchlessEncoder(Robot robotin) {
		this.robotin = robotin;
	}
	
	public void initialize() {
		m_timer.reset();
		quiza=true;
	}
	
	public void execute() {
		robotin.AvanzarTouchless(78.75);
	}
	
	protected boolean isFinished() {
		return quiza;
	}
	
	protected void interrupted() {
		System.out.println("Se Disableleo");
		quiza = true;
	}
}
