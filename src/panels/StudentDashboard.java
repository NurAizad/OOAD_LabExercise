package panels;

import java.awt.*;
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
        typePanel.add (typeLabel);
        typePanel.add (typeCombo);
        centerContainer.add (typePanel);

        // Uploading Material

        JPanel filePanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        
        



    }
    
}
