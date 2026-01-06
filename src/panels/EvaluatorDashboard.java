package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;

public class EvaluatorDashboard extends JPanel
{
    public EvaluatorDashboard(CardLayout cardLayout, JPanel cardManager)
    //public EvaluatorDashboard()
    {
        //setLayout(new GridBagLayout());
        setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        //TOP TITLE
        //DASHBOARD PANEL
        // JPanel dashboardPanel = new JPanel();
        // dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
        // dashboardPanel.setBackground(Color.LIGHT_GRAY);
        // dashboardPanel.setOpaque(true);
        // add(dashboardPanel, gbc);

        // JLabel dashboardLabel = new JLabel("EVALUATOR DASHBOARD", SwingConstants.CENTER);
        // dashboardLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // dashboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        // dashboardLabel.setAlignmentY(TOP_ALIGNMENT);
        // //dashboardPanel.add(dashboardLabel);
        // add(dashboardLabel, BorderLayout.NORTH);
        
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        add(centerContainer, gbc);

        //BUTTONS
        Dimension buttonSize = new Dimension(200, 30);

        //REVIEW SUBMISSIONS BUTTON
        JPanel reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(reviewButtonPanel);

        JButton reviewButton = new JButton("View Presentations");
        reviewButton.setPreferredSize(buttonSize);
        reviewButtonPanel.add(reviewButton);

        //EVALUATE SUBMISSIONS BUTTON
        JPanel evaluateButtonPanel = new JPanel();
        evaluateButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(evaluateButtonPanel);

        JButton evaluateButton = new JButton("Evaluate Presentations");
        evaluateButton.setPreferredSize(buttonSize);
        evaluateButtonPanel.add(evaluateButton);

        //LOGOUT BUTTON
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        JPanel logoutButtonPanel = new JPanel();

        logoutButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(logoutButtonPanel);
        logoutButtonPanel.add(logoutButton);

        setVisible(true);

        //---ACTION LISTENERS---
        logoutButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "MainPanel");
                
            }
        });

       
    }
    
}