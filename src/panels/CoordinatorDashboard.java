package panels;

import java.awt.*;
import javax.swing.*;
//import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class CoordinatorDashboard extends JPanel
{
    public CoordinatorDashboard(CardLayout cardLayout, JPanel cardManager, String name)
    {
        //setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

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
        centerContainer.setBackground(getBackground());
        //centerContainer.setPreferredSize(new Dimension (600, 400));
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        add(centerContainer, gbc);

        JLabel welcomeLabel = new JLabel("Welcome, " + name); //LATER NI DELETE NI DEBUG PURPOSES
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(welcomeLabel);

        //BUTTONS
        Dimension buttonSize = new Dimension(200, 30);

        Color buttonColor = new Color(216, 223, 231);

        //REVIEW SUBMISSIONS BUTTON
        JPanel overviewButtonPanel = new JPanel();
        overviewButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(overviewButtonPanel);

        JButton overviewButton = new JButton("Overview Statistics");
        overviewButton.setPreferredSize(buttonSize);
        overviewButton.setBackground(buttonColor);
        overviewButtonPanel.add(overviewButton);

        //EVALUATE SUBMISSIONS BUTTON
        JPanel createsessionButtonPanel = new JPanel();
        createsessionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(createsessionButtonPanel);

        JButton createsessionButton = new JButton("Create Session");
        createsessionButton.setPreferredSize(buttonSize);
        createsessionButton.setBackground(buttonColor);
        createsessionButtonPanel.add(createsessionButton);

        JPanel generatescheduleButtonPanel = new JPanel();
        generatescheduleButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(generatescheduleButtonPanel);

        JButton generatescheduleButton = new JButton("Generate Schedule");
        generatescheduleButton.setPreferredSize(buttonSize);
        generatescheduleButton.setBackground(buttonColor);
        generatescheduleButtonPanel.add(generatescheduleButton);

        JPanel awardnominationButtonPanel = new JPanel();
        awardnominationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(awardnominationButtonPanel);

        JButton awardnominationButton = new JButton("Award Nomination");
        awardnominationButton.setPreferredSize(buttonSize);
        awardnominationButton.setBackground(buttonColor);
        awardnominationButtonPanel.add(awardnominationButton);

        //LOGOUT BUTTON
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setBackground(buttonColor);
        JPanel logoutButtonPanel = new JPanel();

        logoutButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(logoutButtonPanel);
        logoutButtonPanel.add(logoutButton);

        setVisible(true);

        overviewButton.addActionListener(e -> {
            cardLayout.show(cardManager, "Overview");
        });

        createsessionButton.addActionListener(e -> {
            cardLayout.show(cardManager, "CreateSessionPanel");
        });

        generatescheduleButton.addActionListener(e -> {
            cardLayout.show(cardManager, "GenerateSchedulePanel");
        });

        awardnominationButton.addActionListener(e -> {
            cardLayout.show(cardManager, "AwardNominationPanel");
        });

        //---ACTION LISTENERS---
        logoutButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "MainPanel");
                
            }
        });

       
    }
    
}