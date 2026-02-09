package panels;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File; 
import javax.swing.*;
import javax.swing.border.LineBorder;

public class StudentDashboard extends JPanel{
    public StudentDashboard(CardLayout cardLayout, JPanel cardManager, String name) 
    {
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout (new BoxLayout (centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setPreferredSize(new Dimension(400,300));
        centerContainer.setBorder (BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        add (centerContainer, gbc);

        JLabel welcomeLabel = new JLabel("Welcome, " + name);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(welcomeLabel);

        // Button

        Dimension buttonSize = new Dimension (180, 30);
        Color buttonColor = new Color(216, 223, 231);

        JPanel registerPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        centerContainer.add(registerPanel);

        JButton registerButton = new JButton("Register Presentation");
        registerButton.setPreferredSize(buttonSize);
        registerButton.setBackground(buttonColor);
        registerPanel.add(registerButton);

        // Award Nomination Button

        JPanel awardNominationButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton awardNominationButton = new JButton("Award Nomination");
        awardNominationButton.setBackground(buttonColor);
        awardNominationButton.setPreferredSize (buttonSize);
        awardNominationButtonPanel.add(awardNominationButton);
        centerContainer.add(awardNominationButtonPanel);

        // Logout Button

        JPanel logoutButtonPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JButton logoutButton = new JButton ("Logout");
        logoutButton.setPreferredSize (buttonSize);
        logoutButton.setBackground (buttonColor);
        logoutButtonPanel.add (logoutButton);
        centerContainer.add (logoutButtonPanel);

        add (centerContainer, gbc);

        setVisible (true);

        registerButton.addActionListener (new ActionListener ()
        {
            public void actionPerformed (ActionEvent e) 
            {
                Submission studRegister = new Submission(cardLayout, cardManager, name);
                cardManager.add(studRegister, "studRegister"); 
                cardLayout.show (cardManager, "studRegister");
            }
        });

        awardNominationButton.addActionListener(e -> {
            String currentUser = name;
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

        logoutButton.addActionListener (new ActionListener ()
        {
            public void actionPerformed (ActionEvent e) 
            {
                cardLayout.show (cardManager, "MainPanel");

            }
        });

        
        
    }
}


        