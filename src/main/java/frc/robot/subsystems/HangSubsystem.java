// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HangerConstants;
import frc.robot.RobotContainer;


public class HangSubsystem extends SubsystemBase
{
    private final WPI_VictorSPX m_hang_R = new WPI_VictorSPX(7);
    private final WPI_VictorSPX m_hang_L = new WPI_VictorSPX(5);
    private final WPI_TalonFX m_hang_M = new WPI_TalonFX(9);

    private final PIDController PID_RightHang = new PIDController(
            HangerConstants.KPID_RightHang_P,
            HangerConstants.KPID_RightHang_I,
            HangerConstants.KPID_RightHang_D);

    private final PIDController PID_LeftHang = new PIDController(
            HangerConstants.KPID_LeftHang_P,
            HangerConstants.KPID_LeftHang_I,
            HangerConstants.KPID_LeftHang_D);
    
    private final PIDController PID_MiddleHang = new PIDController(
            HangerConstants.KPID_MiddleHang_P,
            HangerConstants.KPID_MiddleHang_I,
            HangerConstants.KPID_MiddleHang_D);

    private final DoubleSolenoid DSol_1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,0);

    private final DigitalInput Right_Limit = new DigitalInput(HangerConstants.KLimit_R);
    private final DigitalInput Left_Limit = new DigitalInput(HangerConstants.KLimit_L);

    private final Encoder Right_Encoder = new Encoder(
            HangerConstants.KEncoder_R_Channel1,
            HangerConstants.KEncoder_R_Channel2,
            HangerConstants.KEncoder_R_Reversed);
    private final Encoder Left_Encoder = new Encoder(
            HangerConstants.KEncoder_L_Channel1,
            HangerConstants.KEncoder_L_Channel2,
            HangerConstants.KEncoder_L_Reversed);

    private int PID_SideHangDistance_Setpoint = 0;
    private int PID_MiddleHang_Setpoint = HangerConstants.MiddleHang_BenchmarkPosition;
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

    public void Confirm_Benchmark() {
        m_hang_R.set(0.3);
        m_hang_L.set(0.3);
        m_hang_M.set(0.3);
    }
    public void HangMotor(int POV, boolean ButtonA, boolean ButtonB) {
            switch (POV){
                case 0:
                    PID_SideHangDistance_Setpoint += 100;
                    break;
                case 180:
                    PID_SideHangDistance_Setpoint -= 100;
                    break;
                default:
                    PID_SideHangDistance_Setpoint += 0;
        }
        if (ButtonA){
            //中間吊掛上升
            PID_MiddleHang_Setpoint += 100;
        }
        if (ButtonB) {
            //中間吊掛下降
            PID_MiddleHang_Setpoint -= 100;
        }
    }

    @Override
    public void periodic() {
        double CurrentRightHang_PercentOutput = MathUtil.clamp(PID_RightHang.calculate(Right_Encoder.getDistance(), PID_SideHangDistance_Setpoint), HangerConstants.KPID_SideHang_PowerLowerLimit, HangerConstants.KPID_SideHang_PowerUpperLimit);
        if (!Right_Limit.get() && CurrentRightHang_PercentOutput > 0) {
            Right_Encoder.reset();
            CurrentRightHang_PercentOutput=0;
        }
        double CurrentLeftHang_PercentOutput = MathUtil.clamp(PID_LeftHang.calculate(Left_Encoder.getDistance(), PID_SideHangDistance_Setpoint), HangerConstants.KPID_SideHang_PowerLowerLimit, HangerConstants.KPID_SideHang_PowerUpperLimit);
        if (!Left_Limit.get() && CurrentLeftHang_PercentOutput > 0) {
            Left_Encoder.reset();
            CurrentLeftHang_PercentOutput=0;
        }
        double CurrentMiddleHang_PercentOutput = MathUtil.clamp(PID_MiddleHang.calculate(m_hang_M.getSelectedSensorPosition(), PID_MiddleHang_Setpoint), HangerConstants.KPID_MiddleHang_PowerLowerLimit, HangerConstants.KPID_MiddleHang_PowerUpperLimit);
        if(m_hang_M.getSelectedSensorPosition() <= HangerConstants.MiddleHang_BenchmarkPosition && CurrentMiddleHang_PercentOutput > 0) {
            CurrentMiddleHang_PercentOutput = 0;
        }
        m_hang_R.set(ControlMode.PercentOutput, CurrentRightHang_PercentOutput);
        m_hang_L.set(ControlMode.PercentOutput, CurrentLeftHang_PercentOutput);
        m_hang_M.set(ControlMode.PercentOutput, CurrentMiddleHang_PercentOutput);

        SmartDashboard.putNumber("Side_RightHang_Encoder", Right_Encoder.getDistance());
        SmartDashboard.putNumber("Side_LeftHang_Encoder", Left_Encoder.getDistance());
        SmartDashboard.putNumber("MiddleHang_Encoder", m_hang_M.getSelectedSensorPosition());
    }

    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
}
