package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

	

public class AutonomoPosicion1 extends Command {

	boolean quiza;
	private Robot robotin;
	private Timer time = new Timer();
	String gameData;

	public AutonomoPosicion1(Robot robotin) {
		this.robotin = robotin;
	}
	
	
	public void initialize () {
		quiza = false;
		time.reset();
		time.start();
	
	}
	
	
	public void execute () {
		robotin.AvanzarTouchless2(261); 
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
//Este autonomo es para cuando el robot este posicionado en una de las orillas y no vaya a poner el power cube en el switch
	
}
