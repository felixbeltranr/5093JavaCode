package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TouchlessEncoder extends Command {
	Robot robotin;
	private Timer m_timer = new Timer();
	boolean quizaa;
	
	public TouchlessEncoder(Robot robotin) {
		this.robotin = robotin;
		System.out.println("Fue instanciado");
		
		/*Touchless.setMaxPeriod(.1);
		Touchless.setDistancePerPulse(5);
		Touchless.setReverseDirection(true);
		Touchless.setSamplesToAverage(7);*/
    }
	
public void initialize() {
		System.out.println("Fue inicializado");
		robotin.inicializarTouchless();
		m_timer.reset();
		m_timer.start();
		quizaa = false;	
	}

public void execute() {
	
	robotin.AvanzarTouchless(40);
	
	/*double vueltas = 0;
	if (prueba2>0) {
		vueltas= vueltas + 1;
	}
	
	System.out.println(vueltas);*/

	if (m_timer.get() > 30.0) {
		quizaa = true;
	}
}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quizaa;
	}
	
	 protected void interrupted() {
		 System.out.println("Se Disableleo");
		 quizaa = true;
	 }
	
}