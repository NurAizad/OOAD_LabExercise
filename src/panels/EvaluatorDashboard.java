package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EvaluatorDashboard extends JPanel
{
    public EvaluatorDashboard(CardLayout cardLayout, JPanel cardManager)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        //JLabel evaluatorLabel = new JLabel("EVALUATOR DASHBOARD", SwingConstants.CENTER);
        //evaluatorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        
        add(evaluatorLabel, gbc);
    }
}