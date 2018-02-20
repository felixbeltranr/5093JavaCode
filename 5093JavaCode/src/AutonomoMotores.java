

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomoMotores extends Command {
	SpeedController motorLeft;
	SpeedController motorRight;
	private ADXRS450_Gyro gyro450;
	private Timer m_timer = new Timer();
	boolean quiza;



	public AutonomoMotores(SpeedController motorRight, SpeedController motorLeft, ADXRS450_Gyro gyro450) {
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
		this.gyro450 = gyro450;
		
		// TODO Auto-generated constructor stub
	}



	public void initialize () {
		m_timer.reset();
		gyro450.calibrate();
		gyro450.reset();
	}
	
	public void execute () {
		 
		while (m_timer.get() < 30) {
			motorLeft.set(0.5);
			motorRight.set(0.5);
			double angulo = gyro450.getAngle();
			System.out.println(angulo);
			Timer.delay(.2);
			if (angulo >= 360.0 || angulo <=-360.0) {
				angulo = 0;
			}
		}
		quiza = false;

	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
