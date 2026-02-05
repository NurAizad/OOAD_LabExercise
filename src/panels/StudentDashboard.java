package panels;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
public class StudentDashboard extends JPanel{
    public StudentDashboard(CardLayout cardLayout, JPanel cardManager) 
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout (new BoxLayout (centerContainer, BoxLayout.Y_AXIS));
        add (centerContainer, gbc);

        // Button

        Dimension buttonSize = new Dimension (200, 30);
        Dimension labelSize = new Dimension (150, 25);

        // Research title

        JPanel titlePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel titleLabel = new JLabel ("Research Title: ");
        titleLabel.setPreferredSize (labelSize);
        JTextField titleField = new JTextField (20);
        titlePanel.add (titleLabel);
        titlePanel.add (titleField);
        centerContainer.add (titlePanel);

        // Abstract 

        JPanel abstractPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel abstractLabel = new JLabel ("Abstract: ");
        abstractLabel.setPreferredSize (labelSize);
        JTextArea abstractArea = new JTextArea (3, 20);
        abstractArea.setLineWrap (true);
        abstractArea.setWrapStyleWord (true);
        abstractPanel.add (abstractLabel);
        abstractPanel.add (new JScrollPane (abstractArea));
        centerContainer.add (abstractPanel);

        // Supervisor

        JPanel supervisorPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel supervisorLabel = new JLabel ("Supervisor: ");
        supervisorLabel.setPreferredSize (labelSize);
        JTextField supervisorField = new JTextField (20);
        supervisorPanel.add (supervisorLabel);
        supervisorPanel.add (supervisorField);
        centerContainer.add (supervisorPanel);

        // Presentation 

        JPanel typePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10,10));
        JLabel typeLabel = new JLabel ("Presentation Type:");
        typeLabel.setPreferredSize (labelSize);
       
        String[] presentationTypes = {"Poster", "Oral"};
        JComboBox<String> typeCombo = new JComboBox<String> (presentationTypes);
        typeCombo.setPreferredSize (new Dimension (220, 25));
        
        typePanel.add (typeLabel);
        typePanel.add (typeCombo);
        centerContainer.add (typePanel);

        // Uploading Material

        JPanel filePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JLabel fileLabel = new JLabel ("Material Path: ");
        fileLabel.setPreferredSize (labelSize);
        JTextField fileField = new JTextField (12);
        JButton browseButton = new JButton ("Browse");
        filePanel.add (fileLabel);
        filePanel.add (fileField);
        filePanel.add (browseButton);
        centerContainer.add (filePanel);

        // For Register Button 

        JPanel submitButtonPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JButton submitButton = new JButton ("Register Seminar");
        submitButton.setPreferredSize (buttonSize);
        submitButtonPanel.add (submitButton);
        centerContainer.add (submitButtonPanel);

        // Logout Button

        JPanel logoutButtonPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JButton logoutButton = new JButton ("Logout");
        logoutButton.setPreferredSize (buttonSize);
        logoutButtonPanel.add (logoutButton);
        centerContainer.add (logoutButtonPanel);

        setVisible (true);

        // Action Listeners 

        browseButton.addActionListener (e -> 
        {
            JFileChooser fileChooser = new JFileChooser ();
            if (fileChooser.showOpenDialog (this) == JFileChooser.APPROVE_OPTION)
            {
                fileField.setText (fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        submitButton.addActionListener (e -> 
        {
            try (FileWriter writer = new FileWriter ("csvFiles/registrationsCSV.csv", true))
            {
                writer.write (titleField.getText() + "," +
                              supervisorField.getText() + "," +
                              typeCombo.getSelectedItem() + "," +
                              fileField.getText() + "\n");

                JOptionPane.showMessageDialog (this, "Registration Succesful!");
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog (this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
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
