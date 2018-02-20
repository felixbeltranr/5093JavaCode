package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoCalibrarGyro extends Command {
	
	Robot robotin;
	Timer timer = new Timer();
	boolean quiza;
	
	public AutonomoCalibrarGyro(Robot robotin) {
		this.robotin = robotin;
	}
	
	public void initialize() {
		quiza = false;
		robotin.CalibrarGyro();
		System.out.println("Ya me calibraron :)");
		timer.reset();
		timer.start();
	}

	public void execute() {
		while (timer.get() < 1) {
			
		}
		quiza = true;
		
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
