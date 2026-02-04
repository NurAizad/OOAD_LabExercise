package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;

public class CoordinatorDashboard extends JPanel
{
    public CoordinatorDashboard(CardLayout cardLayout, JPanel cardManager)
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
        JPanel overviewButtonPanel = new JPanel();
        overviewButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(overviewButtonPanel);

        JButton overviewButton = new JButton("Overview Statistics");
        overviewButton.setPreferredSize(buttonSize);
        overviewButtonPanel.add(overviewButton);

        //EVALUATE SUBMISSIONS BUTTON
        JPanel createsessionButtonPanel = new JPanel();
        createsessionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(createsessionButtonPanel);

        JButton createsessionButton = new JButton("Create Session");
        createsessionButton.setPreferredSize(buttonSize);
        createsessionButtonPanel.add(createsessionButton);

        JPanel generatescheduleButtonPanel = new JPanel();
        generatescheduleButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(generatescheduleButtonPanel);

        JButton generatescheduleButton = new JButton("Generate Schedule");
        generatescheduleButton.setPreferredSize(buttonSize);
        generatescheduleButtonPanel.add(generatescheduleButton);

        JPanel awardnominationButtonPanel = new JPanel();
        awardnominationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(awardnominationButtonPanel);

        JButton awardnominationButton = new JButton("Award Nomination");
        awardnominationButton.setPreferredSize(buttonSize);
        awardnominationButtonPanel.add(awardnominationButton);

        //LOGOUT BUTTON
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        JPanel logoutButtonPanel = new JPanel();

        logoutButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(logoutButtonPanel);
        logoutButtonPanel.add(logoutButton);

        setVisible(true);

        overviewButton.addActionListener(e -> {
            cardLayout.show(cardManager, "OverviewPanel");
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