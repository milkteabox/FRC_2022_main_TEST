// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class HangerConstants{
        public static final int KEncoder_R_Channel1 = 8;
        public static final int KEncoder_R_Channel2 = 9;
        public static final boolean KEncoder_R_Reversed = false;

        public static final int KEncoder_L_Channel1 = 6;
        public static final int KEncoder_L_Channel2 = 7;
        public static final boolean KEncoder_L_Reversed = false;

        public static final int KLimit_R = 5;
        public static final int KLimit_L = 4;

        public static final double KPID_RightHang_P = 0.1;
        public static final double KPID_RightHang_I = 0;
        public static final double KPID_RightHang_D = 0;

        public static final double KPID_LeftHang_P = 0.1;
        public static final double KPID_LeftHang_I = 0;
        public static final double KPID_LeftHang_D = 0;
        public static final double KPID_SideHang_PowerUpperLimit = 1;
        public static final double KPID_SideHang_PowerLowerLimit = -1;

//        public static final double KPID_MiddleHang_P = 0.1;
//        public static final double KPID_MiddleHang_I = 0;
//        public static final double KPID_MiddleHang_D = 0;
//        public static final double KPID_MiddleHang_PowerUpperLimit = 1;
//        public static final double KPID_MiddleHang_PowerLowerLimit = -1;
        public static final int MiddleHang_BenchmarkPosition = 1000; //中間吊掛最低點（AbsoluteEncoderUnit）
    }

    public static class DriveConstants{

    }

    public static class ShootConstants{

    }
}
