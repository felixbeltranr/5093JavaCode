package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomous1 extends Command {
	private DifferentialDrive m_robotDrive;
	//private Tim0 0er m_timer = new Timer();
	boolean quiza;
	int contador = 0;
	//double CIRCUNFERENCIACENTIMETROS = 47.75220838;
	private Encoder CimCoder;
	private Encoder CimCoder2;
	double DistancePerRevolution = 470.75220838/8.0;  // milimetros
    double PulsesPerRevolution = 20.0;
    double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;
	
	public Autonomous1(DifferentialDrive m_robotDrive, Encoder CimCoder, Encoder CimCoder2) {
    	//requires(Robot.turret);
		this.m_robotDrive = m_robotDrive;
		this.CimCoder = CimCoder;
		this.CimCoder2 = CimCoder2;
	//no se habia subido	
    }
	
	private void resetEncoders() {
		CimCoder.reset();
		CimCoder2.reset();
	}
	
	public void initialize() {
		//m_timer.reset();
		//m_timer.start();
		
		quiza = false;
		
		CimCoder.setDistancePerPulse(DistancePerPulse);
		CimCoder2.setDistancePerPulse(DistancePerPulse);
		
		resetEncoders();

	}
	
	private double getAverageEncoderPosition() {
		if(contador < CimCoder.getRaw()) {
			System.out.println("Cim1: " + CimCoder.getDistance());
			System.out.println("Cim2: " + CimCoder2.getDistance());
			System.out.println("Cim1 raw: " + CimCoder.getRaw());
			System.out.println("Cim2 raw: " + CimCoder2.getRaw());
			contador = CimCoder.getRaw();
		}

		return (CimCoder.getDistance() + CimCoder2.getDistance()) / 2;
	}
	
	public void Avanza (double milimetros) {
		
		//80 porque es el numero de marcas por vuelta. 8 porque por cada 8 vueltas del motor la llanta da 1 vuelta.
		/*
		double vuelta = (milimetros*1870/1000)-50;//(centimetros*80)*8/CIRCUNFERENCIACENTIMETROS;
		double valor = 0;
		double valor2 = 0;
		double promedioEncoders=0;
		
		while (promedioEncoders < vuelta) {
			valor = CimCoder.getDistance();
			valor2 = CimCoder2.getDistance();
			promedioEncoders = (valor+valor2)/2;
			//valor = CimCoder.getDistance(); //antes estaba siendo multiplicado por -1
			//valor2 = -1*CimCoder2.getDistance();
			System.out.println("Valor destino: " + vuelta + " Valor actual: " + valor);
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		} */
		resetEncoders();
		do {
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		}while(getAverageEncoderPosition() < milimetros);
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
		quiza=true;
		
	}
	
	
	public void execute() {
		//m_robotDrive.arcadeDrive(0, 0.5);
		Avanza(2000.0);//en milimetros
		
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return quiza;
	}
	//El tocino reinara el mundo
	// Called once after isFinished returns true
    /*protected void end() {
    	HoldAngle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }*/
	




}