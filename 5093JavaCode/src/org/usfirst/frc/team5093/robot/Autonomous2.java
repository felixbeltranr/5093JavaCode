package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomous2 extends Command {
	private AnalogInput Ultri;
	private double vmm = (293.0/30000.0);
	private double voltaje = 0;
	private double distancia = 0;
	private Timer m_timer = new Timer();
	boolean quiza2;
	
	public Autonomous2(AnalogInput Ultri) {
		this.Ultri = Ultri;
    }
	
	public void initialize() {
		
		m_timer.reset();
		m_timer.start();
		quiza2 = false;
	}

	public void execute() {
		
			try {
				voltaje = Ultri.getVoltage();
				distancia = voltaje/vmm;
				System.out.println(distancia);
				if (m_timer.get() > 5.0) {
						quiza2 = true;
				}
			}
			catch(Exception e) {
				System.out.println("error: " + e.getMessage() );
			}
				
			
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return quiza2;
	}
	

}
