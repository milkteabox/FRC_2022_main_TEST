// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShootSubsystem extends SubsystemBase
{
    private final WPI_TalonFX m_Falcon500 = new WPI_TalonFX(11);
    private final WPI_VictorSPX m_Redline775 = new WPI_VictorSPX(10);

    public void StartShoot(boolean shoot){
        if (shoot){
            m_Falcon500.set(0.9);
        }
    }    /** Creates a new ExampleSubsystem. */
    public ShootSubsystem() {
    }
    public void ShootMotorRED(boolean Redshoot){
        if(Redshoot){
            m_Redline775.set(1);
        }
}
    
    
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
}
