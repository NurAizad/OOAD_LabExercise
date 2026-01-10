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