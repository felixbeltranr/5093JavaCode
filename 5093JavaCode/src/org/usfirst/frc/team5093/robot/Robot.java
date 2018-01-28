/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5093.robot;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//	private Gyro gyro;
	private AnalogInput Ultri = new AnalogInput(3);
	private DifferentialDrive m_robotDrive = new DifferentialDrive(new Spark(0), new Spark(1));
	private XboxController m_stick = new XboxController(0);
	//private Timer m_timer = new Timer();
	private Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	Autonomous1 Auto1;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	//	gyro = new ADXRS450_Gyro();
		
		Auto1 = new Autonomous1(m_robotDrive);
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Auto 1", Auto1);
		autoChooser.addObject("Auto 2", new Autonomous2(Ultri));
		
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
		
		
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		/*m_timer.reset();
		m_timer.start();*/
		autonomousCommand = (Command) autoChooser.getSelected();
		System.out.println("Autonomo: " + autonomousCommand.getClass() );
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
		// Drive for 2 seconds
		//double distancia = Ultri.getVoltage();
		//System.out.println(distancia);
		//double angle = gyro.getAngle();
		//System.out.println(angle);
	 /*	if (m_timer.get() < 0.0) {
			m_robotDrive.arcadeDrive(0.8, 0.0); // drive forwards half speed
		} else {
			m_robotDrive.stopMotor(); // stop robot
		}*/
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		//m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
		//double power = m_stick.getThrottle(); -//Se mueve cn el gatillo derecho
		//boolean cosita = m_stick.getAButton();
		double power = m_stick.getTriggerAxis(null);
		double xAxis = m_stick.getX();
		
		System.out.println(xAxis);				
		m_robotDrive.arcadeDrive(power, xAxis);
		
		if (power > 0 && xAxis == 0) {
			System.out.println("Esta avanzando al frente");
		} if (power > 0 && xAxis > 0) {
			System.out.println("girando a la derecha");
		} if (power >0 && xAxis <0 ) {
			System.out.println("girando a la izquierda");
		} else {
			System.out.println("El robot no se esta moviendo");
		}
		
		//System.out.println(power);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
