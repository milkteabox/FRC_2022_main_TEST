// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.math.*;


public class DriveSubsystem extends SubsystemBase
{
    private final MotorControllerGroup m_left = new MotorControllerGroup(new WPI_VictorSPX(1),
            new WPI_VictorSPX(2));
    private final  MotorController m_right = new MotorControllerGroup(new WPI_VictorSPX(3),
            new WPI_VictorSPX(4));
    private final WPI_VictorSPX m_intake = new WPI_VictorSPX(8);
    private final DifferentialDrive m_drive = new DifferentialDrive(m_left,m_right);

    public void drive(double X,double Y){
        //遙桿死區
        if(Math.abs(X) <= 0.3) {
            if (X < 0) {
                X = -0.3;
            }
            else if (X > 0) {
                X = 0.3;
            }
        }
        if(Math.abs(Y) <= 0.3) {
            if (Y < 0) {
                Y = -0.3;
            }
            else if (Y > 0) {
                Y = 0.3;
            }
        }
        m_drive.arcadeDrive(Y,X);
    }
    public void collect(double speed){
        m_intake.set(speed);
    }
    /** Creates a new ExampleSubsystem. */
    
    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
    }
    
    
    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
    public void auto(){

    }
}
