package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

	

public class AutonomoPosicion1 extends Command {

	private DifferentialDrive m_robotDrive;
	boolean quiza;
	int s=0;
	int contador = 0;
	private Encoder CimCoder;
	private Encoder CimCoder2;
	double DistancePerRevolution = 470.75220838/8.0;  // milimetros
	double PulsesPerRevolution = 20.0;
	double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;
	
	public AutonomoPosicion1(DifferentialDrive m_robotDrive, Encoder CimCoder, Encoder CimCoder2) {
		this.m_robotDrive = m_robotDrive;
		this.CimCoder = CimCoder;
		this.CimCoder2 = CimCoder2;
	}

	
	
	private void resetEncoders() {
		CimCoder.reset();
		CimCoder2.reset();
	}
	
	public void initialize () {
		quiza = false;
		
		CimCoder.setDistancePerPulse(DistancePerPulse);
		CimCoder2.setDistancePerPulse(DistancePerPulse);
		
		resetEncoders();
	
	}
	
	private double getAverageEncoderPosition() {
		//if(contador < CimCoder.getRaw()) {
			System.out.println("Cim1: " + CimCoder.getDistance());
			//System.out.println("Cim2: " + CimCoder2.getDistance());
			System.out.println("Cim1 raw: " + CimCoder.getRaw());
			//System.out.println("Cim2 raw: " + CimCoder2.getRaw());
			//contador = CimCoder.getRaw();
		//}

		//return (CimCoder.getDistance() + CimCoder2.getDistance()) / 2;
		return CimCoder.getDistance();
	}
	
	public void Avanza (double milimetros) {
		resetEncoders();
		double cantidad = milimetros-15;
				
		do {
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		}while(getAverageEncoderPosition() < cantidad); 
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
		quiza=true;

	
		
	}
	
	public void execute () {
		Avanza (6629.4);
		quiza=true;

	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}
//Este autonomo es para cuando el robot este posicionado en una de las orillas y no vaya a poner el power cube en el switch
	
}
