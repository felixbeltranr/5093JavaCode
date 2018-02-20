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
		robotin.resetEncoders();
		//robotin.ResetGyro();
	}
	
	public void execute() {
		
		
		robotin.AvanzarTouchless2(40);
		Timer.delay(0.2);
		robotin.ResetGyro();
		robotin.Girar(90);
		Timer.delay(0.2);
		robotin.AvanzarTouchless2(20);
		Timer.delay(0.2);
		robotin.ResetGyro();
		robotin.Girar(-90);
		Timer.delay(0.2);
		robotin.AvanzarTouchless2(20);
		Timer.delay(0.2);
		robotin.ResetGyro();
		robotin.Girar(90);
		
		//robotin.LevantarTijeras(2.0);
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
