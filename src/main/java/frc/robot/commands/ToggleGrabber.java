package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class ToggleGrabber extends CommandBase {

    private final Grabber m_Grabber;

    public ToggleGrabber(Grabber grabber) {
        m_Grabber = grabber;
        addRequirements(m_Grabber);
    }

    @Override
    public void initialize() {
        m_Grabber.toggleGrabber();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
