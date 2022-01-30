// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class HangSubsystem extends SubsystemBase
{
    private final WPI_VictorSPX m_hang_R = new WPI_VictorSPX(7);
    private final WPI_VictorSPX m_hang_L = new WPI_VictorSPX(5);
    private final WPI_TalonFX m_hang_M = new WPI_TalonFX(9);
    private final DoubleSolenoid DSol_1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,0);
//    private final Encoder e_hang_R = new Encoder(2,3);
//    private final Encoder e_hang_L = new Encoder(4,5);
    private boolean HangStay = false;
    /** Creates a new ExampleSubsystem. */
    public void HangSoleniod(boolean forward,boolean reverse){
        if(forward){
            DSol_1.set(DoubleSolenoid.Value.kForward);
        }
        else if(reverse){
            DSol_1.set(DoubleSolenoid.Value.kReverse);
        }
        else{
            DSol_1.set(DoubleSolenoid.Value.kOff);
        }
    }
    private void m_hang_ALL(double A_motor){
        m_hang_L.set(A_motor);
        m_hang_R.set(A_motor);
    }
    public void HangMotor(int POV,boolean ButtomB,boolean ButtomX){
            switch (POV){
                case 0:
                    m_hang_ALL(1);
                    break;
                case  45:
                    m_hang_R.set(1);
                    break;
                case 135:
                    m_hang_R.set(-1);
                    break;
                case 180:
                    m_hang_ALL(-1);
                    break;
                case 225:
                    m_hang_L.set(-1);
                    break;
                case 315:
                    m_hang_L.set(1);
                    break;
                default:
                    m_hang_ALL(0);
        }
            if (ButtomB){
                m_hang_M.set(0.3);
            }
            else if(ButtomX){
                m_hang_M.set(-0.3);
            }
            else{
                m_hang_M.set(0);
            }


    }
    public HangSubsystem() {

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
