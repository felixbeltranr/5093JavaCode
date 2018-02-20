package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoFMS extends Command{
	
	Robot robotin;
	boolean quiza;
	String gameData;
	
	
	public AutonomoFMS(Robot robotin) {
		this.robotin=robotin; 
	
	}
	
	public void initialize() {
		
		quiza = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}
	
	public void execute() {
		
		if(gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				System.out.println("Primer switch de tu color esta a la izquierda");
				robotin.AvanzarTouchless2(40);
				Timer.delay(0.2);
				robotin.Girar(-90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(100);
				Timer.delay(0.2);
				robotin.Girar(-90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(-116.2678);
				
			}else {
				System.out.println("Primer switch de tu color esta a la derecha");
				robotin.AvanzarTouchless2(40);
				Timer.delay(0.2);
				robotin.Girar(90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(81.1593);
				Timer.delay(0.2);
				robotin.Girar(-90);
				Timer.delay(0.2);
				robotin.AvanzarTouchless2(111.8585);
				
			}
		}
		
		
		if (gameData.charAt(1) == 'L') {
			System.out.println("Segundo switch de tu color esta a la izquierda");
		} else {
			System.out.println("Segundo switch de tu color esta a la derecha");

		}
		
		
		
		if (gameData.charAt(2) == 'L') {
			System.out.println("Tercer switch de tu color esta a la izquierda");
		} else {
			System.out.println("Tercer switch de tu color esta a la derecha");

		}
		
		quiza = true;
	}
	
	protected boolean isFinished() {
		
		return quiza;
	}

}
