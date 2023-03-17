package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ArmPivot;

public class PivotArm extends CommandBase {

    private final ArmPivot m_Pivot;
    private CommandXboxController m_Controller;

    public PivotArm(ArmPivot pivot, CommandXboxController controller) {
        m_Pivot = pivot;
        m_Controller = controller;
        addRequirements(m_Pivot);
    }

    @Override
    public void end(boolean interrupted) {
        m_Pivot.setMotor(0);
    }

    @Override
    public void execute() {
        m_Pivot.setMotor(-m_Controller.getLeftY());
    }
    
}
