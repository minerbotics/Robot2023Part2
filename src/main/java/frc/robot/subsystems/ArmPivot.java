package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {

    private final CANSparkMax m_ArmMotor;
    private final RelativeEncoder m_Encoder;
    private final PIDController m_PidController;
    private final ArmFeedforward m_Feedforward;

    private double m_CurrentPosition;

    public ArmPivot() {
        m_ArmMotor = new CANSparkMax(
            ArmConstants.PIVOT_MOTOR, 
            CANSparkMaxLowLevel.MotorType.kBrushless);

        m_Encoder = m_ArmMotor.getEncoder();

        m_PidController = new PIDController(ArmConstants.kP, ArmConstants.kI, ArmConstants.kD);
        m_PidController.enableContinuousInput(0, Math.PI * 2);
        m_PidController.setTolerance(.25);

        m_Feedforward = new ArmFeedforward(ArmConstants.kS, ArmConstants.kG, ArmConstants.kV, ArmConstants.kA);

        setPosition(ArmConstants.Position.STOWED.getPivot());
    }

    @Override
    public void periodic() {
        double pidMotorSpeed = m_PidController.calculate(m_Encoder.getPosition(), m_CurrentPosition);

        setMotor(
            MathUtil.clamp((pidMotorSpeed) + m_Feedforward.calculate(m_CurrentPosition, 0),
            -ArmConstants.maxMotorVoltage,
            ArmConstants.maxMotorVoltage)
        );
    }

    public void resetEncoder() {
        m_Encoder.setPosition(0);
    }

    public void setMotor(double voltage) {
        m_ArmMotor.setVoltage(voltage);
    }

    public Command setPose(double position) {
        return run(() -> setPosition(position)).until(() -> atSetPoint());
    }

    public void setPosition(double position) {
        m_CurrentPosition = position;
    }

    public double getPosition() {
        return m_CurrentPosition;
    }

    public boolean atSetPoint() {
        return m_PidController.atSetpoint();
    }
}
