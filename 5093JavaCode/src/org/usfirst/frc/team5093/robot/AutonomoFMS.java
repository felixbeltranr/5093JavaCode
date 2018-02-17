package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoFMS extends Command{
	
	boolean quiza;
	String gameData;
	
	
	public AutonomoFMS() {
		
	
	}
	
	public void initialize() {
		
		quiza = false;
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}
	
	public void execute() {
		
		if(gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				System.out.println("Primer switch de tu color esta a la izquierda");
			}else {
				System.out.println("Primer switch de tu color esta a la derecha");
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
