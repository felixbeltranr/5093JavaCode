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
	//double DistancePerRevolution = 541.3625/8.0;//470.75220838/8.0;milimetros
	//double PulsesPerRevolution = 20.0;
	//double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;
	double PulsePerDistance = 1.21;

	public AutonomoPosicion2(ADXRS450_Gyro gyro450, DifferentialDrive m_robotDrive, Encoder CimCoder, Encoder CimCoder2) {
		// TODO Auto-generated constructor stub
		this.m_robotDrive = m_robotDrive;
		this.gyro450 = gyro450;
		this.CimCoder = CimCoder;
		this.CimCoder2 = CimCoder2;
	}
	
	private void resetEncoders() {
		CimCoder.reset();
		CimCoder2.reset();
	}

	public void initialize(){
		m_timer.reset();
		gyro450.calibrate();
		//CimCoder.setDistancePerPulse(DistancePerPulse);
		//CimCoder2.setDistancePerPulse(DistancePerPulse);
		
		resetEncoders();
		gyro450.reset();
	}
	
	private double getAverageEncoderPosition() {
		//if(contador < CimCoder2.getRaw()) {
			System.out.println("Cim1_2: " + CimCoder.getDistance());
			System.out.println("Cim2_2: " + CimCoder2.getDistance());
			System.out.println("Cim1 raw: " + CimCoder.getRaw());
			System.out.println("Cim2 raw: " + CimCoder2.getRaw());
			contador = CimCoder2.getRaw();
		//}

		//return CimCoder.getDistance();
		return CimCoder2.getRaw() / PulsePerDistance;
	}
	
	public void Avanza (double milimetros) {
		resetEncoders();
		do {
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		}while(getAverageEncoderPosition() < milimetros);
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
	}

	public void Girar (double gradosMeta) {     //metodo de sofia que no ha terminado por cierto y esta mal hecho, lo hara el legendario Ulises de la mancha
		gyro450.reset();
		int gradosInt = 0;
		int sentido = 1;
		if(gradosMeta < 0) {
			sentido = -1;
		}

		double angulo = 0.0;
		while (angulo < gradosMeta*sentido) {
			m_robotDrive.curvatureDrive(0.0, sentido*0.75, true);
			angulo = gyro450.getAngle()*sentido;
			if(gradosInt < (int)angulo) {
				gradosInt = (int)angulo*sentido;
				System.out.println(gradosInt);
			}
		}
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
	}
	
	
	public void execute() {
		
		Avanza(1000.0);
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