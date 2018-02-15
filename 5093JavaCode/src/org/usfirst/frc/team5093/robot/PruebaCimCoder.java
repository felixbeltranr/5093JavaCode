 

package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PruebaCimCoder extends Command {
	private Encoder CimCoder = new Encoder(2,3);
	private Timer m_timer = new Timer();
	boolean quizaa;
	
	public PruebaCimCoder(Encoder CimCoder) {
		this.CimCoder = CimCoder;
    }
	
public void initialize() {
		
		m_timer.reset();
		m_timer.start();
		quizaa = false;
	}

public void execute() {
	
	double prueba1 = CimCoder.get(); //Rango de 20 unidades y cambia de enteros
	double prueba2 = CimCoder.getDistance(); //Rango de 20 unidades y cambia de .25
	double prueba3 = CimCoder.getRaw(); //Rango de 80 unidades y cambia de enteros
	double prueba4 = CimCoder.getRate(); //Lee la velocidad del giro
	System.out.println(prueba1);
	
	if (m_timer.get() > 10.0) {
		quizaa = true;
}
}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return quizaa;
	}

}
