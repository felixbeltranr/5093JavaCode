package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoTalonVictor extends Command {

	boolean quiza;
	private Timer time = new Timer();
	private Robot robotin;
	
	public AutonomoTalonVictor (Robot robotin) {
		
		this.robotin = robotin;
		//mesa que mas aplauda si
	}
	public void initialize(){
		//comentario para subirlo
		quiza=false;
		time.reset();
		time.start();
		robotin.CalibrarGyro();
		robotin.ResetGyro();
		robotin.reverseMotor();
	}
	
	public void execute() {
		
			//robotin.AvanzarTalonVictor(50);
			robotin.GirarTalonVictor(90);
			/*robotin.AvanzarTalonVictor(-50);
			robotin.GirarTalonVictor(-90);*/
			System.out.println("Corriendo");
			System.out.println("Termino");
		quiza=true;
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quiza;
	}
}
