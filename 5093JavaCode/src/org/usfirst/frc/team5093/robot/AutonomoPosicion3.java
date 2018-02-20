package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoPosicion3 extends Command{
	boolean quiza;
	private Robot robotin;
	private Timer time = new Timer();
	String gameData;

	public AutonomoPosicion3(Robot robotin) {
		this.robotin = robotin;
		
	}
	
	
	public void initialize () {
		quiza = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		time.reset();
		time.start();
	
	}
	
	
	public void execute () {
		if(gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				System.out.println("Primer switch de tu color esta a la izquierda");
				robotin.AvanzarTouchless2(261); 

				
			}else {
				System.out.println("Primer switch de tu color esta a la derecha");
				robotin.AvanzarTouchless2(40);
				Timer.delay(0.2);
				robotin.Girar(-90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(31.6356);
				Timer.delay(0.2);
				robotin.Girar(90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(111.8585);
				
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