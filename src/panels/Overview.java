package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class Overview extends JPanel
{
    public Overview(CardLayout cardLayout, JPanel cardManager)
    {
        setBackground(new Color(245,245,245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createCompoundBorder
            (
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));
        add(centerContainer, gbc);

        Color buttonColor = new Color(216, 223, 231);

        //TITLE ----------------------------------------------------------------------------------------------
        JLabel overviewLabel = new JLabel("OVERVIEW", SwingConstants.CENTER);
        overviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 24));
        centerContainer.add(overviewLabel);


        //BUTTONS ---------------------------------------------------------------------------------------------

        Dimension buttonSize = new Dimension(200, 30);

        //INDIVIDUAL REPORTS BUTTON
        JPanel individualButtonPanel = new JPanel();
        individualButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton individualReportsButton = new JButton("Individual Reports");
        individualReportsButton.setBackground(buttonColor);
        individualReportsButton.setPreferredSize(buttonSize);
        individualButtonPanel.add(individualReportsButton);
        centerContainer.add(individualButtonPanel);

        //OVERALL SUMMARY BUTTON
        JPanel summaryButtonPanel = new JPanel();
        summaryButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton overallSummaryButton = new JButton("Overall Summary");
        overallSummaryButton.setBackground(buttonColor);
        overallSummaryButton.setPreferredSize(buttonSize);
        summaryButtonPanel.add(overallSummaryButton);
        centerContainer.add(summaryButtonPanel);

        //BACK TO DASHBOARD 
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton backToDashboardButton = new JButton("Back to Dashboard");
        backToDashboardButton.setBackground(buttonColor);
        backToDashboardButton.setPreferredSize(new Dimension(200, 30));
        bottomButtonPanel.add(backToDashboardButton);
        centerContainer.add(bottomButtonPanel);


        //ACTION LISTENERS -----------------------------------------------------------------------------------
        individualReportsButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "IndividualReports");
                
            }
            
        });

        overallSummaryButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "OverallSummary");
                
            }
            
        });

        backToDashboardButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "CoordinatorPanel");
                
            }
            
        });

        setVisible(true);
    }
}