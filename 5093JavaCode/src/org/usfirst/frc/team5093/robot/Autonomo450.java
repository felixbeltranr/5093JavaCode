package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomo450 extends Command{
	private Timer g_timer = new Timer();
	private ADXRS450_Gyro gyro450;
	private Timer m_timer = new Timer();
	boolean quiza;
	private DifferentialDrive m_robotDrive;
	
	//Hola

	public Autonomo450(ADXRS450_Gyro gyro450, DifferentialDrive m_robotDrive) {
		// TODO Auto-generated constructor stub
		this.m_robotDrive = m_robotDrive;
		this.gyro450 = gyro450;
	}

	public void initialize(){
		m_timer.reset();
		gyro450.calibrate();
		gyro450.reset();
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
		
		/*while (m_timer.get() < 5) {
			m_robotDrive.arcadeDrive(0.0, 0.5);
		}*/
		
		/*while (m_timer.get() < 30) {
			double angulo = gyro450.getAngle();
			System.out.println(angulo);
			Timer.delay(.2);
			if (angulo >= 360.0 || angulo <=-360.0) {
				angulo = 0;
			}
		}*/
		
		Girar(90);
		
		quiza = true;
	}
	
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}

}