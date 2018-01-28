package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Autonomous2 extends Command {
	private AnalogInput Ultri;
	private double vmm = (293.0/30000.0);
	private double voltaje = 0;
	private double distancia = 0;
	private Timer m_timer = new Timer();
	boolean quiza2;
	
	public Autonomous2(AnalogInput Ultri) {
		this.Ultri = Ultri;
    }
	
	public void initialize() {
		
		m_timer.reset();
		m_timer.start();
		quiza2 = false;
	}

	public void execute() {
		
			try {
				voltaje = Ultri.getVoltage();
				distancia = voltaje/vmm;
				System.out.println(distancia);
				if (m_timer.get() > 5.0) {
						quiza2 = true;
				}
			}
			catch(Exception e) {
				System.out.println("error: " + e.getMessage() );
			}
				
			
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return quiza2;
	}
	/* public Aim() {
    	requires(Robot.turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SetTargetAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CorrectAngle();
 \   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return AtRightAngle();
 \   }

    // Called once after isFinished returns true
    protected void end() {
    	HoldAngle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    	}*/
	
    }