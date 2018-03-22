package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoTalonSRX extends Command{
	boolean quiza;
	private Timer time = new Timer();
	private Robot robotin;
	
	public AutonomoTalonSRX(Robot robotin) {
		this.robotin = robotin;
	}
	public void initialize() {
		quiza = false;
		time.reset();
		time.start();
	}
	
	public void execute() {
		while (time.get() < 4) {
			//robotin.PruebaTalon();
			System.out.println("Corriendo");
		}
		System.out.println("Termino");
		//robotin.DetenerTalon();
		quiza=true;
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}
}
