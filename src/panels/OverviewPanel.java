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
        add(centerContainer, gbc);

        //TITLE ----------------------------------------------------------------------------------------------
        JLabel overviewLabel = new JLabel("OVERVIEW PAGE", SwingConstants.CENTER);
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 20));
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

        //ACTION LISTENERS (targets can be implemented later)
        individualReportsButton.addActionListener(e -> {
            cardLayout.show(cardManager, "IndividualReportsPanel");
        });

        overallSummaryButton.addActionListener(e -> {
            cardLayout.show(cardManager, "OverallSummaryPanel");
        });

        setVisible(true);
    }
}
