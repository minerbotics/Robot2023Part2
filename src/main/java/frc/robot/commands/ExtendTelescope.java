package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmTelescope;

public class ExtendTelescope extends CommandBase {
    
    private final ArmTelescope m_Telescope;

    public ExtendTelescope(ArmTelescope telescope) {
        m_Telescope = telescope;
        addRequirements(m_Telescope);
    }

    @Override
    public void end(boolean interrupted) {
        m_Telescope.setMotor(0);
    }

    @Override
    public void execute() {
        m_Telescope.setMotor(-1);
    }
}
