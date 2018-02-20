package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoPosicion2_1 extends Command {
	
	Robot robotin;
	Timer m_timer = new Timer();
	boolean quiza;
	
	public AutonomoPosicion2_1 (Robot robotin) {
		
		this.robotin = robotin;
		
	}
	
	public void initialize(){
		m_timer.reset();
		m_timer.start();
		//robotin.CalibrarGyro();
	}
	
	public void execute() {
		
		
		robotin.AvanzarTouchless2(40);
		Timer.delay(0.2);
		robotin.Girar(90);
		Timer.delay(0.2);
		robotin.AvanzarTouchless2(68.53);
		Timer.delay(0.2);
		robotin.Girar(-90);
		Timer.delay(0.2);
		robotin.AvanzarTouchless2(103.85);
		Timer.delay(0.2);

		
		quiza = true;
	}
	
	protected void interrupted() {
		System.out.println("Se Disableleo");
		quiza = true;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}

}
