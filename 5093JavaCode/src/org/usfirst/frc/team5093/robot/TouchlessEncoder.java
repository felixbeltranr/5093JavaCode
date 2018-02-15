package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class TouchlessEncoder extends Command {
	private Counter Touchless = new Counter(2);
	private Timer m_timer = new Timer();
	boolean quizaa;
	
	public TouchlessEncoder(Counter Touchless) {
		this.Touchless = Touchless;
    }
	
public void initialize() {
		
		m_timer.reset();
		m_timer.start();
		quizaa = false;
		Touchless.setMaxPeriod(.1);
		Touchless.setDistancePerPulse(5);
		Touchless.setReverseDirection(true);
		Touchless.setSamplesToAverage(7);
	}

public void execute() {
	
	double prueba1 = Touchless.get(); //Rango de 20 unidades y cambia de enteros
	double prueba2 = Touchless.getDistance(); //Rango de 20 unidades y cambia de .25
	double prueba3 = Touchless.getRate(); //Lee la velocidad del giro
	
	
	/*double vueltas = 0;
	if (prueba2>0) {
		vueltas= vueltas + 1;
	}
	
	System.out.println(vueltas);*/
	
	System.out.println(prueba1);
	
	if (m_timer.get() > 10.0) {
		quizaa = true;
	}
}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quizaa;
	}
	
}