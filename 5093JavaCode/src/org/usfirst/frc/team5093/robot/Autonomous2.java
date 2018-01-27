package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Autonomous2 extends Command {
	private AnalogInput Ultri;
	private double vmm = (293.0/30000.0);
	private double voltaje = 0;
	private double distancia = 0;
	private Timer m_timer = new Timer();
	
	public void initialize() {
		Ultri = new AnalogInput(3);
		m_timer.reset();
		m_timer.start();
	}

	public void execute() {
		try {
			voltaje = Ultri.getVoltage();
			distancia = voltaje/vmm;
			System.out.println(distancia);
		}
		catch(Exception e) {
			System.out.println("error: " + e.getMessage() );
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return false;
	}
	

}
