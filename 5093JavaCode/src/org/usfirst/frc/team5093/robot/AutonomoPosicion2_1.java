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
		robotin.CalibrarGyro();
		robotin.resetEncoders();
		robotin.ResetGyro();
	}
	
	public void execute() {
		
		robotin.Avanza(1000.0);
		/*Avanza(1016.0);
		Timer.delay(1);
		Girar(90);
		Timer.delay(1);
		Avanza(1740.6);//1740.6);
		Timer.delay(1);
		Girar(-90);
		Timer.delay(1);
		Avanza(2637.7);*/
		
		quiza = true;
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}

}
