package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;

public class Grabber extends SubsystemBase {

    private final DoubleSolenoid m_GrabberSolenoid;

    public Grabber() {
        m_GrabberSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM,
            GrabberConstants.FORWARD_CHANNEL,
            GrabberConstants.REVERSE_CHANNEL);
    }

    public void grab() {
        m_GrabberSolenoid.set(Value.kReverse);
    }

    public void release() {
        m_GrabberSolenoid.set(Value.kForward);
    }

    public void toggleGrabber() {
        String grabberPosition;
        if (this.isGrabbing()) {
            grabberPosition = "In";
            this.release();
        } else {
            grabberPosition = "Out";
            this.grab();
        }
        SmartDashboard.putString("Grabber Position", grabberPosition);
    }

    public boolean isGrabbing() {
        return m_GrabberSolenoid.get() == Value.kReverse;
    }
    
}
