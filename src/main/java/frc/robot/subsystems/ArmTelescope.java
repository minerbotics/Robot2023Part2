package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmTelescope extends SubsystemBase {

    private final WPI_VictorSPX m_TelescopeMotor;

    public ArmTelescope() {
        m_TelescopeMotor = new WPI_VictorSPX(ArmConstants.TELESCOPE_MOTOR);
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void in(double speed) {
    m_TelescopeMotor.set(-speed);
  }

  public void stop() {
    m_TelescopeMotor.set(0);
  }

  public void out(double speed) {
    m_TelescopeMotor.set(speed);
  }

}
