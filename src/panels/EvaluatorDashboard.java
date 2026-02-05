package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EvaluatorDashboard extends JPanel
{
    public EvaluatorDashboard(CardLayout cardLayout, JPanel cardManager, String evaluatorName)
    {
        //setBackground(Color.LIGHT_GRAY);
        setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        add(centerContainer, gbc);

        JLabel welcomeLabel = new JLabel("Welcome, " + evaluatorName); //LATER NI DELETE NI DEBUG PURPOSES
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(welcomeLabel);

        //BUTTONS
        Dimension buttonSize = new Dimension(200, 30);
        Color buttonColor = new Color(216, 223, 231);

        //REVIEW SUBMISSIONS BUTTON
        JPanel reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //reviewButtonPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(reviewButtonPanel);

        JButton reviewButton = new JButton("View Presentations");
        reviewButton.setPreferredSize(buttonSize);
        reviewButton.setBackground(buttonColor);
        reviewButtonPanel.add(reviewButton);

        //EVALUATE SUBMISSIONS BUTTON
        
        JPanel evaluateButtonPanel = new JPanel();
        evaluateButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //evaluateButtonPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(evaluateButtonPanel);

        JButton evaluateButton = new JButton("Evaluate Presentations");
        evaluateButton.setPreferredSize(buttonSize);
        evaluateButton.setBackground(buttonColor);
        evaluateButtonPanel.add(evaluateButton);
        
        

        //LOGOUT BUTTON
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setBackground(buttonColor);
        JPanel logoutButtonPanel = new JPanel();
        //logoutButtonPanel.setBackground(Color.LIGHT_GRAY);

        logoutButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(logoutButtonPanel);
        logoutButtonPanel.add(logoutButton);

        setVisible(true);

        //---ACTION LISTENERS---
        logoutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "MainPanel");
                
            }
        });

        reviewButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPresentation viewPresentationPanel = new ViewPresentation(cardLayout, cardManager, evaluatorName);
                cardManager.add (viewPresentationPanel, "ViewPresentationPanel");
                cardLayout.show(cardManager, "ViewPresentationPanel");
            }
        }); 

        evaluateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evaluate evaluatePanel = new Evaluate(cardLayout, cardManager, evaluatorName);
                cardManager.add (evaluatePanel, "EvaluatePanel");
                cardLayout.show(cardManager, "EvaluatePanel");
            }
        });

       
    }
    
}