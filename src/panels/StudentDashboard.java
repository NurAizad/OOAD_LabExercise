package panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class StudentDashboard extends JPanel{

    // private JTextField titleField;
    // private JTextArea abstractArea;
    // private JTextField supervisorField;
    // private JTextField fileField;
    // private JComboBox<String> typeCombo;

    // private File selectedFile;

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

        //Dimension buttonSize = new Dimension(200, 30);
        add (centerContainer, gbc);

        JLabel welcomeLabel = new JLabel("Welcome, " + name); //LATER NI DELETE NI DEBUG PURPOSES
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(welcomeLabel);

        // Button

        Dimension buttonSize = new Dimension (200, 30);
        //Dimension labelSize = new Dimension (150, 25);
        Color buttonColor = new Color(216, 223, 231);

        JPanel registerPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        centerContainer.add(registerPanel);

        JButton registerButton = new JButton("Register Presentation");
        registerButton.setPreferredSize(buttonSize);
        registerButton.setBackground(buttonColor);
        registerPanel.add(registerButton);

        //VOTING

        JPanel votePanel = new JPanel();
        votePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(votePanel);

        JButton voteButton = new JButton("Vote Best Presenter");
        voteButton.setPreferredSize(buttonSize);
        voteButton.setBackground(buttonColor);
        votePanel.add(voteButton);

        // Research title

        /* 
        JPanel titlePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel titleLabel = new JLabel ("Research Title: ");
        titleLabel.setPreferredSize (labelSize);
        
        titleField = new JTextField (20);
        titleField.setPreferredSize(inputDim);
        titlePanel.add (titleLabel);
        titlePanel.add (titleField);
        centerContainer.add (titlePanel);

        // Abstract 

        JPanel abstractPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel abstractLabel = new JLabel ("Abstract: ");
        abstractLabel.setPreferredSize (labelSize);
        abstractArea = new JTextArea (3, 20);
        abstractArea.setLineWrap (true);
        abstractArea.setWrapStyleWord (true);

        abstractPanel.add (abstractLabel);
        abstractPanel.add (new JScrollPane (abstractArea));
        centerContainer.add (abstractPanel);

        // Supervisor

        JPanel supervisorPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel supervisorLabel = new JLabel ("Supervisor: ");
        supervisorLabel.setPreferredSize (labelSize);
        supervisorField = new JTextField (20);
        supervisorField.setPreferredSize(inputDim);
        supervisorPanel.add (supervisorLabel);
        supervisorPanel.add (supervisorField);
        centerContainer.add (supervisorPanel);

        // Presentation 

        JPanel typePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10,10));
        JLabel typeLabel = new JLabel ("Preferred Presentation:");
        typeLabel.setPreferredSize (labelSize);
       
        String[] presentationTypes = {"Poster", "Oral"};
        typeCombo = new JComboBox<String> (presentationTypes);
        typeCombo.setBackground(buttonColor);
        typeCombo.setPreferredSize (new Dimension (200, 25));
        
        typePanel.add (typeLabel);
        typePanel.add (typeCombo);
        centerContainer.add (typePanel);

        // Uploading Material

        JPanel filePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel fileLabel = new JLabel ("Material Path: ");
        fileLabel.setPreferredSize (labelSize);
        fileField = new JTextField (10);
        JButton browseButton = new JButton ("Browse");
        browseButton.setBackground (buttonColor);
        filePanel.add (fileLabel);
        filePanel.add (fileField);
        filePanel.add (browseButton);
        centerContainer.add (filePanel);

        // For Register Button 

        JPanel submitButtonPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JButton submitButton = new JButton ("Register");
        submitButton.setBackground (buttonColor);
        submitButton.setPreferredSize (buttonSize);
        submitButtonPanel.add (submitButton);
        centerContainer.add (submitButtonPanel);
        */

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
                StudRegister studRegister = new StudRegister(cardLayout, cardManager, name);
                cardManager.add(studRegister, "studRegister"); 
                cardLayout.show (cardManager, "studRegister");
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
        // Action Listeners 


        