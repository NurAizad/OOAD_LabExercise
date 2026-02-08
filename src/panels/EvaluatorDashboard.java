package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class EvaluatorDashboard extends JPanel
{
    public EvaluatorDashboard(CardLayout cardLayout, JPanel cardManager, String evaluatorName)
    {
        setBackground(new Color (245, 245, 245));
        setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
       // centerContainer.setPreferredSize(new Dimension (600, 400));
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        add(centerContainer, gbc);

        JLabel welcomeLabel = new JLabel("Welcome, " + evaluatorName); //LATER NI DELETE NI DEBUG PURPOSES
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(welcomeLabel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 20)));

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

        //VOTE BUTTON
        // JPanel votePanel = new JPanel();
        // votePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // centerContainer.add(votePanel);

        // JButton voteButton = new JButton("Vote Best Presenter");
        // voteButton.setPreferredSize(buttonSize);
        // voteButton.setBackground(buttonColor);
        // votePanel.add(voteButton);
        
        //AWARD NOMINATION BUTTON
        JPanel awardNominationButtonPanel = new JPanel();
        awardNominationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //awardNominationButtonPanel.setBackground(Color.WHITE); // match other panels
        centerContainer.add(awardNominationButtonPanel);

        JButton awardnominationButton = new JButton("Award Nomination");
        awardnominationButton.setPreferredSize(buttonSize);
        awardnominationButton.setBackground(buttonColor);
        awardNominationButtonPanel.add(awardnominationButton);

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

        //ACTION LISTENERS
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

        awardnominationButton.addActionListener(e -> {
            String currentUser = evaluatorName;
            boolean hasVoted = false;

            File votedFile = new File("csvFiles/votedUsersCSV.csv");

            //check if user has voted
            if (votedFile.exists()) {
                try (Scanner scanner = new Scanner(votedFile)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine().trim();
                        if (line.equalsIgnoreCase(currentUser)) {
                            hasVoted = true;
                            break;
                        }
                    }
                } 
                
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                        null,
                        "Error checking previous votes.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            if (!hasVoted) {
                new AwardNomination(currentUser);
            } 
            
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "You have completed the nomination.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

       
    }
    
}