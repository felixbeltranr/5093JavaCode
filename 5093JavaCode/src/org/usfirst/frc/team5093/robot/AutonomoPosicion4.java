package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class AutonomoPosicion4 extends Command{
	Robot robotin;
	boolean quiza;
	String gameData;
	private Timer time = new Timer();

	public AutonomoPosicion4(Robot robotin) {
		// TODO Auto-generated constructor stub
		this.robotin = robotin;
		
	}
	
	
	public void initialize(){
		quiza = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		time.reset();
		time.start();
	}

	
	public void execute() {
		if(gameData.length() > 0) {
			if (gameData.charAt(0) == 'R') {
				System.out.println("Primer switch de tu color esta a la derecha");
				robotin.AvanzarTouchless2(-261); 

				
			}else {
				System.out.println("Primer switch de tu color esta a la izquierda");
				//EL ROBOT EMPIEZA ALREVES
				robotin.AvanzarTouchless2(-40);
				Timer.delay(0.2);
				robotin.Girar(-90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(27.4763);
				Timer.delay(0.2);
				robotin.Girar(90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(-111.8585);
				robotin.DejarCubo();
				
			}
		}
		quiza=true;

		
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