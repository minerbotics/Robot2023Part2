package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ArmTelescope;

public class MoveTelescope extends CommandBase {
    
    private final ArmTelescope m_Telescope;
    private CommandXboxController m_Controller;

    public MoveTelescope(ArmTelescope telescope, CommandXboxController controller) {
        m_Telescope = telescope;
        m_Controller = controller;
        addRequirements(m_Telescope);
    }

    @Override
    public void end(boolean interrupted) {
        m_Telescope.stop();
    }

    @Override
    public void execute() {
        m_Telescope.in(m_Controller.getRightY());
    }
}
