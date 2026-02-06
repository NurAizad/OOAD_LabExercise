package panels;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OverviewPanel extends JPanel
{
    public OverviewPanel(CardLayout cardLayout, JPanel cardManager)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        gbc.insets = new Insets(100, 0, 0, 0); // top, left, bottom, right padding
        add(centerContainer, gbc);

        //TITLE ----------------------------------------------------------------------------------------------
        JLabel overviewLabel = new JLabel("OVERVIEW", SwingConstants.CENTER);
        overviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        gbcBottom.gridy = 1;         // row below the centerContainer
        gbcBottom.weightx = 1.0;
        gbcBottom.weighty = 1.0;     // push it to bottom
        gbcBottom.anchor = GridBagConstraints.PAGE_END;
        add(bottomButtonPanel, gbcBottom);

        //ACTION LISTENERS (targets can be implemented later)
        individualReportsButton.addActionListener(e -> {
            cardLayout.show(cardManager, "IndividualReportsPanel");
        });

        overallSummaryButton.addActionListener(e -> {
            cardLayout.show(cardManager, "OverallSummaryPanel");
        });

        backToDashboardButton.addActionListener(e -> {
            cardLayout.show(cardManager, "CoordinatorDashboard");
        });

        setVisible(true);
    }
}
