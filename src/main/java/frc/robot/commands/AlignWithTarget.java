package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Swerve;
import frc.robot.subsystems.Limelight;

public class AlignWithTarget extends CommandBase {

    private final Swerve m_swerve;
    private final Limelight m_Limelight;

    public AlignWithTarget(Swerve swerve, Limelight limelight) {
        m_swerve = swerve;
        
        m_Limelight = limelight;
        addRequirements(m_swerve, m_Limelight);
        TeleopSwerve m_TeleopSwerve = new TeleopSwerve(m_swerve, () -> 1, () -> 2, () -> 5, () -> true);
    }

    @Override
    public void execute() {
        if (m_Limelight.getTV() != 1.0) {
            return;
        }
        double tx = m_Limelight.getTX();
        if (tx < -2) {
            new TeleopSwerve(m_swerve, null, null, null, null)
            //strafe right
            m_swerve.drive(new ChassisSpeeds(0.0, 0.1, 0.0));
        } else if (tx > 2) {
            //strafe left
            m_swerve.drive(new ChassisSpeeds(0.0, -0.1, 0.0));
        } else {
            m_swerve.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {

    }

  // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} 