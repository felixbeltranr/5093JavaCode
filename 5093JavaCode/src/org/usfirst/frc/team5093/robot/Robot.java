/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5093.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
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
	private AnalogGyro gyro1 = new AnalogGyro(0);
	private ADXRS450_Gyro gyro450 = new ADXRS450_Gyro();
	private AnalogInput Ultri = new AnalogInput(3);
	
	private SpeedController motorLeft = new Spark(1);//1
	private SpeedController motorRight = new Spark (0);//0
	
	private DifferentialDrive m_robotDrive = new DifferentialDrive(motorLeft, motorRight);
	private XboxController m_stick = new XboxController(0);
	private Timer m_timer = new Timer();
	private Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	Autonomous1 Auto1;
	//private Joystick joy = new Joystick(0);
	private Encoder CimCoder = new Encoder(8, 9, true); //6, 7, true); //Izquierdo
	private Encoder CimCoder2 = new Encoder(4, 5, false); //8, 9, false); //Derecho
	private DifferentialDrive tijeras = new DifferentialDrive(new Spark(6), new Spark (7));
	//private DifferentialDrive pinza = new DifferentialDrive(new Spark(3), new Spark (6));//eran 4 y 5
	
	//private Counter Touchless = new Counter (2);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	//	gyro = new ADXRS450_Gyro();
		
		//Auto1 = new Autonomous1(m_robotDrive, CimCoder, CimCoder2);
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Auto 1", Auto1);
		autoChooser.addObject("Auto 2", new Autonomous2(Ultri));
		//autoChooser.addObject("AutoGyro", new AutonomoGyro(m_robotDrive, gyro1));
		autoChooser.addObject("AutoGyro450", new Autonomo450(gyro450, m_robotDrive));
		autoChooser.addObject("AutoPos1", new AutonomoPosicion1(m_robotDrive, CimCoder, CimCoder2));
		autoChooser.addObject("AutoPos2", new AutonomoPosicion2(gyro450, m_robotDrive, CimCoder, CimCoder2));
		autoChooser.addObject("Autonomo para FMS", new AutonomoFMS());
		//autoChooser.addObject("AutoMotores1 NO USAAAR PLIS", new AutonomoMotores(motorRight, motorLeft, gyro450));
		//autoChooser.addObject("Auto3 Counter", new TouchlessEncoder(Touchless));
		
			
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
		gyro450.calibrate();
		gyro450.reset();
		//CimCoder.reset();
	}

	//No se habia subido
	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		//m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
		//double power = m_stick.getThrottle(); -//Se mueve cn el gatillo derecho
		//boolean cosita = m_stick.getAButton();
		//AQUI ESTA EL CODIGO PARA MOVER EL ROBOT CON XBOX 
		try{
			double xAxis = (m_stick.getX(Hand.kRight))/2;
			double power = -(m_stick.getY(Hand.kRight))/2;
			//double powercito = power*;
			double graditos = gyro450.getAngle();
			m_robotDrive.curvatureDrive(power, xAxis, true);
			
			System.out.println(xAxis + "    Power: " + power);
			
			double gatilloDUp = m_stick.getTriggerAxis(Hand.kRight); //Con este baja
			tijeras.curvatureDrive(0, gatilloDUp, true);
			double gatilloDDown = m_stick.getTriggerAxis(Hand.kLeft); //Con este sube
			tijeras.curvatureDrive(0, -gatilloDDown, true);
			//System.out.println("Lectura " + gatilloDUp + gatilloDDown);

			double pinzas = m_stick.getX(Hand.kLeft);
			//pinza.arcadeDrive(pinzas, 0);
			
			/*if (m_stick.getAButton()) {
				m_timer.reset();
				while (m_timer.get() < 1.0) {
					m_robotDrive.arcadeDrive(1, 0);
				}
			} */
			
		
		}
		catch(Exception e) {
			//System.out.println("error: " + e.getMessage() );
		}
																										
		
		
		//m_robotDrive.arcadeDrive(powercito, 0);
		
		/*
		if (power > 0 && xAxis == 0) {
			System.out.println("Esta avanzando al frente");
		} if (power > 0 && xAxis > 0) {Limelight Camera
			System.out.println("girando a la derecha");
		} if (power >0 && xAxis <0 ) {
			System.out.println("girando a la izquierda");
		} else {
			System.out.println("El robot no se esta moviendo");
		}
		*/
	/*
		double powercin = joy.getY(null);
		double x = joy.getX(null);
		//System.out.println(x);
		m_robotDrive.arcadeDrive(powercin/2, 0);*/
		/*double prueba1 = CimCoder.get(); //son 20 unidades por una vuelta (va por unidades enteras)
		double prueba2 = CimCoder.getDistance(); //son 20 unidades por vuelta (va por .25)
		double prueba3 = CimCoder.getRaw(); //son 80 unidades por vuelta (va por unidades enteras)
		double prueba4 = CimCoder.getRate(); //mide velocidad del encoder
		double vueltas = prueba3/80;
		System.out.println(vueltas);*/
		
		
		

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}