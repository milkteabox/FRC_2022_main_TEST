// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.subsystems.ShootSubsystem;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    DriveSubsystem DriveSubsystem = new DriveSubsystem();
    HangSubsystem HangSubsystem = new HangSubsystem();
    ShootSubsystem ShootSubsystem = new ShootSubsystem();
    XboxController controller_X = new XboxController(0);
    Joystick controller_F = new Joystick(1);

    
    private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);
    
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
        DriveSubsystem.setDefaultCommand(new RunCommand(() -> {
            DriveSubsystem.drive(-controller_X.getLeftY(),controller_X.getRightX());
            DriveSubsystem.collect(controller_X.getRightTriggerAxis()-controller_X.getLeftTriggerAxis());
            HangSubsystem.HangSoleniod(controller_X.getYButton(),controller_X.getAButton());
            HangSubsystem.HangMotor(controller_X.getPOV(), controller_X.getBButton(), controller_X.getXButton());
            ShootSubsystem.StartShoot(controller_X.getRightBumper());
            ShootSubsystem.ShootMotorRED(controller_X.getLeftBumper());
        }, HangSubsystem,DriveSubsystem,ShootSubsystem));
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }
}
