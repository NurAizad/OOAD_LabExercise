package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Overview extends JPanel
{
    public Overview(CardLayout cardLayout, JPanel cardManager)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        gbc.insets = new Insets(100, 0, 0, 0);
        add(centerContainer, gbc);

        //TITLE ----------------------------------------------------------------------------------------------
        JLabel overviewLabel = new JLabel("OVERVIEW", SwingConstants.CENTER);
        overviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 24));
        centerContainer.add(overviewLabel);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 20)));


        //BUTTONS ---------------------------------------------------------------------------------------------

        Dimension buttonSize = new Dimension(200, 30);

        //INDIVIDUAL REPORTS BUTTON
        JPanel individualButtonPanel = new JPanel();
        individualButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(individualButtonPanel);

        JButton individualReportsButton = new JButton("Individual Reports");
        individualReportsButton.setPreferredSize(buttonSize);
        individualButtonPanel.add(individualReportsButton);

        //OVERALL SUMMARY BUTTON
        JPanel summaryButtonPanel = new JPanel();
        summaryButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(summaryButtonPanel);

        JButton overallSummaryButton = new JButton("Overall Summary");
        overallSummaryButton.setPreferredSize(buttonSize);
        summaryButtonPanel.add(overallSummaryButton);

        //BACK TO DASHBOARD 
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton backToDashboardButton = new JButton("Back to Dashboard");
        backToDashboardButton.setPreferredSize(new Dimension(200, 30));
        bottomButtonPanel.add(backToDashboardButton);

        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 1; 
        gbcBottom.weightx = 1.0;
        gbcBottom.weighty = 1.0; 
        gbcBottom.anchor = GridBagConstraints.PAGE_END;
        add(bottomButtonPanel, gbcBottom);

        //ACTION LISTENERS (to add: destinationss)
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
