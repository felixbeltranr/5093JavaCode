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
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
//import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private AnalogGyro gyro1 = new AnalogGyro(0);
	//private ADXRS450_Gyro gyro450 = new ADXRS450_Gyro();
	private AnalogInput Ultri = new AnalogInput(2);
	
	private SpeedController motorLeft = new Spark(1);//1
	private SpeedController motorRight = new Spark (0);//0
	
	private DifferentialDrive m_robotDrive = new DifferentialDrive(motorLeft, motorRight);
	private XboxController m_stick = new XboxController(0); //aparece en la izquierda en el dash board
	private XboxController m_stick2 = new XboxController(1); //aparece en la derecha en el dash board
	private Timer m_timer = new Timer();
	private Timer TimerTijeras1 = new Timer();
	private Timer TimerTijeras2 = new Timer();
	
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	
	private VictorSPX victor1 = new VictorSPX(2);//2
	
	private TalonSRX talon1 = new TalonSRX(1);//1
	
	private SpeedController jaguar1 = new Jaguar(4);
	
	private Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	Autonomous1 Auto1;
	//private Joystick joy = new Joystick(0);
	private Encoder CimCoder = new Encoder(8, 9, true); //6, 7, true); //Izquierdo
	private Encoder CimCoder2 = new Encoder(4, 5, true); //8, 9, false); //Derecho
	
	//private DifferentialDrive tijeras = new DifferentialDrive(new Spark(6), new Spark (7));
	//private DifferentialDrive tijerasAbajo = new DifferentialDrive(new Spark(2), new Spark (8));
	private SpeedController pinza = new Spark (9);
	private SpeedController levantaPinza = new Spark (7);
	
	private Counter Touchless = new Counter (3);
	
	int contadorTouchless = 0;
	int contadorCim = 0;
	double PulsePerDistance = 1.27733; //Cantidad de pulsos para 1 milimetro 1.556

	double Diametro = 6.0;
	double PI = 3.14159265359;
	double Circunferencia = Diametro*PI;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//CameraServer.getInstance().startAutomaticCapture();
		autoChooser = new SendableChooser <Command>();
		autoChooser.addDefault("Auto con robot en una esquina para pasar linea", new AutonomoPosicion1(this));
		autoChooser.addObject("Auto con robot en el medio para pasar linea", new AutonomoPosicion2_1(this));
		autoChooser.addObject("Auto con robot en el medio para poner cubo", new AutonomoFMS(this));
		autoChooser.addObject("Calibrar gyro", new AutonomoCalibrarGyro(this));
		autoChooser.addObject("Auto desde esquina derecha para poner cubo", new AutonomoPosicion3(this));
		autoChooser.addObject("Auto desde esquina izquierda para poner cubo", new AutonomoPosicion4(this));
		autoChooser.addObject("Prueba con el VictorSPX", new AutonomoVictor(this));
		autoChooser.addObject("Prueba con el Jaguar", new AutonomoJaguar(this));
		autoChooser.addObject("Prueba con el TalonSRX", new AutonomoTalonSRX(this));
		autoChooser.addObject("Prueba con el Talon y Victor", new AutonomoTalonVictor(this));
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
		
		
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
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
		
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
		//gyro450.calibrate();
		//gyro450.reset();
		reverseMotor();
		System.out.println("Se reverseo");
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		
		double offset = 0.035;
		
		try{
			double xAxis = (m_stick.getX(Hand.kRight));
			double power = -(m_stick.getY(Hand.kRight));
			//double powercito = power*;
			//double graditos = gyro450.getAngle();
			//m_robotDrive.curvatureDrive(power, xAxis, true);
			if (xAxis>offset) {
				talon1.set(ControlMode.PercentOutput, -power);
				victor1.set(ControlMode.PercentOutput, power);
				
			}else if(xAxis<(offset*-1)){
				talon1.set(ControlMode.PercentOutput, power);
				victor1.set(ControlMode.PercentOutput, -power);
			}else {
				talon1.set(ControlMode.PercentOutput, power);
				victor1.set(ControlMode.PercentOutput, power);
			}
			
			System.out.println(xAxis + "    Power: " + power);
			
			double gatilloDDown = m_stick.getTriggerAxis(Hand.kRight); //Con este baja
			double gatilloDUp = m_stick.getTriggerAxis(Hand.kLeft); //Con este sube
			
			/*if (gatilloDUp > 0 && gatilloDDown > 0) {
				tijeras.curvatureDrive(0, 0, true);
			}
			else if (gatilloDDown>0) {
				tijeras.curvatureDrive(0, gatilloDDown, true);
			}
			else if (gatilloDUp>0) {
				tijeras.curvatureDrive(0, -gatilloDUp, true);
			}
			else {
				tijeras.curvatureDrive(0, 0, true);
			}*/

			boolean positivo = m_stick.getAButton();
			boolean negativo = m_stick.getBButton();
			
			/*if (positivo == true && negativo == true) {
				
				tijerasAbajo.curvatureDrive(0, 0, true);
				
			} else if (negativo == true) {
				
				tijerasAbajo.curvatureDrive(0, -1, true);
				
			} else if (positivo == true) {
				
				tijerasAbajo.curvatureDrive(0, 1,true);

			} else {
				
				tijerasAbajo.curvatureDrive(0, 0, true);

			}*/
			/*if (gatilloDUp > 0 && gatilloDDown > 0) {
				tijerasArriba.curvatureDrive(0, 0, true);
				tijerasAbajo.curvatureDrive(0, 0, true);
			}
			else if (gatilloDDown>0) {
				tijerasAbajo.curvatureDrive(0, gatilloDDown, true);
			}
			else if (gatilloDUp>0) {
				tijerasArriba.curvatureDrive(0, -gatilloDUp, true);
			}
			else {
				tijerasArriba.curvatureDrive(0, 0, true);
				tijerasAbajo.curvatureDrive(0, 0, true);
			}*/
			//System.out.println("Lectura " + gatilloDUp + gatilloDDown);

			double pinzas = m_stick.getX(Hand.kLeft);
			double pinzitas = -1*m_stick.getY(Hand.kRight);
			
			/*if (power2 != 0  ) {
				anguloJoy = joy.getZ();
				m_robotDrive.curvatureDrive(power2, anguloJoy, true);
			}*/
			
			pinza.set(pinzas);
			levantaPinza.set(pinzitas);
			
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
	
	public void LevantarTijeras(double segundos) {
		TimerTijeras1.reset();
		TimerTijeras1.start();
		
		 while (TimerTijeras1.get() < segundos) {
		//tijeras.curvatureDrive(0, -1, true);
	}
		 //tijeras.curvatureDrive(0, 0, true);
	}
	
	public double getAverageEncoderPosition() {
		//if(contador < CimCoder2.getRaw()) {
			System.out.println("Cim1_2: " + CimCoder.getDistance());
			System.out.println("Cim2_2: " + CimCoder2.getDistance());
			System.out.println("Cim1 raw: " + CimCoder.getRaw());
			System.out.println("Cim2 raw: " + CimCoder2.getRaw());
			contadorCim = CimCoder2.getRaw();
		//}

		//return CimCoder.getDistance();
		return CimCoder2.getRaw() / PulsePerDistance;
	}
	
	public void resetEncoders() {
		CimCoder.reset();
		CimCoder2.reset();
	}
	
	

	public void Avanza (double milimetros) {
		resetEncoders();
		do {
			m_robotDrive.curvatureDrive(0.3, 0.0, true); // drive forwards half speed
		}while(getAverageEncoderPosition() < milimetros);
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
	}

	
	public void ResetGyro () {
		//gyro450.reset();
	}
	
	public void CalibrarGyro () {
		//gyro450.calibrate();
	}
	
	public void Girar (double gradosMeta) {     //metodo de sofia que no ha terminado por cierto y esta mal hecho, lo hara el legendario Ulises de la mancha
		//gyro450.reset();
		int gradosInt = 0;
		int sentido = 1;
		int Contador4 = 0;
		double powerGiro = 0.65;
		if(gradosMeta < 0) {
			sentido = -1;
			powerGiro = 0.8;
		}

		double angulo = 0.0;
		while (angulo < gradosMeta*sentido) {
			m_robotDrive.curvatureDrive(0.0, sentido*powerGiro, true);
			//angulo = gyro450.getAngle()*sentido;
			if(gradosInt < (int)angulo) {
				gradosInt = (int)angulo*sentido;
				System.out.println(gradosInt);
			}
			if (/*(gyro450.getAngle()*sentido) > ((gradosMeta*sentido)-20) &&*/ Contador4 >= 20){
				powerGiro = powerGiro - (.01*sentido);
				Contador4 = 0;
			}
			
			if (powerGiro*sentido <= 0.55) {
				powerGiro = 0.55;
			}
			Contador4 = Contador4 + 1;
		}
		
		
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
	}
	
	public void PruebaTouchless() {

		//La llanta tiene 12 rayas negras, es decir, por vuelta detecta 12 pulsos

		try {
			double prueba1 = Touchless.get(); //Este metodo lee la cantidad de pulsos (es el indicado para utilizar)
			double prueba2 = Touchless.getDistance(); 
			double prueba3 = Touchless.getRate();
			
			if(contadorTouchless < Touchless.get()) {
			
			System.out.println("prueba1   " + prueba1);
			//System.out.println("prueba2   " + prueba2);
			//System.out.println("prueba3   " + prueba3);
			contadorTouchless = Touchless.get();
			}
		}
		catch(Exception e) {
			System.out.println("error" + e.getMessage());
		}
		
		}
	public void AvanzarTouchless(double DistanciaMeta) {
		Touchless.reset();
		double LecturasMeta = (DistanciaMeta*12)/Circunferencia;
		System.out.println(LecturasMeta);
		double power3 = 0;
		double sentido = 1;
		if (DistanciaMeta > 0) {
			power3 = 0.3;
			sentido = 1;
		} 
		if (DistanciaMeta < 0){
			power3 = -0.3;
			sentido = -1;
		}

		while (Touchless.get() < (LecturasMeta*sentido-2)) {
			m_robotDrive.curvatureDrive(power3, 0.0, true);
			
			if(contadorTouchless < Touchless.get()) {
				
				System.out.println("prueba1   " + Touchless.get());
				//System.out.println("prueba2   " + prueba2);
				//System.out.println("prueba3   " + prueba3);
				contadorTouchless = Touchless.get();
				}
		}
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
	}
	
	public void AvanzarTouchless2 (double Distancia) {
		Touchless.reset();
		contadorTouchless = 0;
		int Contadorcin = 0;
				
		double Meta = (Distancia*12)/Circunferencia;
		System.out.println(Meta);
		double powercin = 0;
		int sentido = 1;
		
		if (Distancia > 0) {
			powercin = 0.3;
			sentido = 1;
		} 
		if (Distancia < 0) {
			powercin = -0.3;
			sentido = -1;
		}
		
		while (Touchless.get() < (Meta*sentido)) {
			m_robotDrive.curvatureDrive(powercin, 0.0, true);
			Contadorcin = Contadorcin + 1;
			
			if(contadorTouchless < Touchless.get()) {
				//System.out.println("prueba1   " + Touchless.get());
				contadorTouchless = Touchless.get();

				}
			
			if (Touchless.get() > (Meta*sentido-6) && Contadorcin >= 20){
				powercin = powercin - .002*sentido;
				Contadorcin = 0;
			}
			
			System.out.println("power" + powercin + "Contador del touchless" + Touchless.get());
		}
		m_robotDrive.curvatureDrive(0.0, 0.0, true);
		
			
	}
	
	public void reverseMotor() {
		talon1.setInverted(true); //Con este metodo los valores postivos puestos en el talon se hacen negativos y viceversa
	}
	public void AvanzarTalonVictor(double DistanciaMeta) {
		Touchless.reset();
		double LecturasMeta = (DistanciaMeta*12)/Circunferencia;
		System.out.println(LecturasMeta);
		double power3 = 0;
		double sentido = 1;
		if (DistanciaMeta > 0) {
			power3 = 0.3;
			sentido = 1;
		} 
		if (DistanciaMeta < 0){
			power3 = -0.3;
			sentido = -1;
		}

		while (Touchless.get() < (LecturasMeta*sentido-2)) {
			talon1.set(ControlMode.PercentOutput, 0.3*sentido);
			victor1.set(ControlMode.PercentOutput, 0.3*sentido);
			
			if(contadorTouchless < Touchless.get()) {
				
				System.out.println("prueba1   " + Touchless.get());
				//System.out.println("prueba2   " + prueba2);
				//System.out.println("prueba3   " + prueba3);
				contadorTouchless = Touchless.get();
				}
		}
		talon1.set(ControlMode.PercentOutput, 0.0);
		victor1.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void GirarTalonVictor (double gradosMeta) {     //metodo de sofia que no ha terminado por cierto y esta mal hecho, lo hara el legendario Ulises de la mancha
		//gyro450.reset();
		int gradosInt = 0;
		int sentido = 1;
		int Contador4 = 0;
		double powerGiro = 0.65;
		if(gradosMeta < 0) {//si gira a la derecha sentido es -1. Imaginemos que el talon esta del lado derecho.
			sentido = -1;
			powerGiro = 0.8;
		}
		//si gira a la derecha, talon necesita valor negativo y victor valor positivo
		//si gira a la izquierda, talon necesita valor positivo y victor talon negativo

		double angulo = 0.0;
		while (angulo < gradosMeta*sentido) {
			talon1.set(ControlMode.PercentOutput, 0.3*sentido);
			victor1.set(ControlMode.PercentOutput, -0.3*sentido);
			//angulo = gyro450.getAngle()*sentido;
			if(gradosInt < (int)angulo) {
				gradosInt = (int)angulo*sentido;
				System.out.println(gradosInt);
			}
			if (/*(gyro450.getAngle()*sentido) > ((gradosMeta*sentido)-20) &&*/ Contador4 >= 20){
				powerGiro = powerGiro - (.01*sentido);
				Contador4 = 0;
			}
			
			if (powerGiro*sentido <= 0.55) {
				powerGiro = 0.55;
			}
			Contador4 = Contador4 + 1;
		}
		

		talon1.set(ControlMode.PercentOutput, 0.0);
		victor1.set(ControlMode.PercentOutput, 0.0);
	}
	
	
	
	
	public void inicializarTouchless() {
		Touchless.reset();
		contadorTouchless = 0;	
	}
	
	public void PruebaVictor() {
		victor1.set(ControlMode.PercentOutput, 0.3);		
	}
	
	public void DetenerVictor() {
		victor1.set(ControlMode.PercentOutput, 0);
	}
	
	public void PruebaJaguar() {
		jaguar1.set(0.3);
	}	
	

	public void DetenerJaguar() {
		jaguar1.set(0);
	}
	
	public void PruebaTalon() {
		talon1.set(ControlMode.PercentOutput, 0.3);
	}
	
	public void DetenerTalon() {
		talon1.set(ControlMode.PercentOutput, 0.0);
	}
	
}