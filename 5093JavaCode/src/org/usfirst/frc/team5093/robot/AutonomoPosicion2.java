package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class AutonomoPosicion2 extends Command{
	private Timer g_timer = new Timer();
	private ADXRS450_Gyro gyro450;
	private Timer m_timer = new Timer();
	boolean quiza;
	int contador = 0;
	private DifferentialDrive m_robotDrive;
	private Encoder CimCoder;
	private Encoder CimCoder2;
	double DistancePerRevolution = 470.75220838/8.0;  // milimetros
	double PulsesPerRevolution = 20.0;
	double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;

	public AutonomoPosicion2(ADXRS450_Gyro gyro450, DifferentialDrive m_robotDrive, Encoder CimCoder, Encoder CimCoder2) {
		// TODO Auto-generated constructor stub
		this.m_robotDrive = m_robotDrive;
		this.gyro450 = gyro450;
		this.CimCoder = CimCoder;
		this.CimCoder2 = CimCoder;
		
	}
	
	private void resetEncoders() {
		CimCoder.reset();
		CimCoder2.reset();
	}

	public void initialize(){
		m_timer.reset();
		gyro450.calibrate();
		CimCoder.setDistancePerPulse(DistancePerPulse);
		CimCoder2.setDistancePerPulse(DistancePerPulse);
		
		resetEncoders();
		gyro450.reset();
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
		resetEncoders();
		do {
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		}while(getAverageEncoderPosition() < milimetros);
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
		quiza=true;
	}

		public void Girar (double gradosMeta) {     //metodo de sofia que no ha terminado por cierto y esta mal hecho, lo hara el legendario Ulises de la mancha
			
				if(gradosMeta>0.0) {
					double angulo = 0;
					while (angulo < gradosMeta) {
						m_robotDrive.curvatureDrive(0.0, 0.5, true);
						angulo = gyro450.getAngle();
						System.out.println(angulo);
					}
					
				}
				if(gradosMeta < 0.0) {
					double angulo = 0;
					while (angulo > gradosMeta) {
						m_robotDrive.curvatureDrive(0.0, -0.5, true);
						angulo = gyro450.getAngle();
						System.out.println(angulo);
					}
					
					m_robotDrive.curvatureDrive(0.0, 0.0, true);
				}
				
			}

	public void Gira(double segundos) {
		g_timer.reset();
		g_timer.start();
		while (g_timer.get() < segundos) {
			m_robotDrive.curvatureDrive(0, 0.3, true);
		} 
		g_timer.reset();
		g_timer.start();
		while (g_timer.get() < segundos) {
			m_robotDrive.curvatureDrive(0, -0.3, true);
		} 
		
		m_robotDrive.curvatureDrive(0, 0, true);
		System.out.println(g_timer);
		
	}
	
	
	public void execute() {
		
		Avanza(1016.0);
		Girar(90);
		Avanza(1778.0);
		Girar(-90);
		Avanza(3048.0);
		
		quiza = true;
	}
	
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}

}