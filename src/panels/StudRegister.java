package panels;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;
//import javax.swing.filechooser.*;
import java.io.File;

public class StudRegister extends JPanel 
{
    private JTextField titleField;
    private JTextArea abstractArea;
    private JTextField supervisorField;
    private JTextField fileField;
    private JComboBox<String> typeCombo;

    private File selectedFile;

    public StudRegister(CardLayout cardLayout, JPanel cardManager, String name) 
    //public StudRegister(CardLayout cardLayout, JPanel cardManager) 
    {
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        //gbc.anchor = GridBagConstraints.CENTER;

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout (new BoxLayout (centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        //centerContainer.setPreferredSize(new Dimension (600, 400));
        centerContainer.setBorder (BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        Dimension inputDim = new Dimension(300, 30);
        add (centerContainer, gbc);

        // JLabel welcomeLabel = new JLabel("Welcome, " + name); //LATER NI DELETE NI DEBUG PURPOSES
        // welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        // centerContainer.add(welcomeLabel);

        // Button

        Dimension buttonSize = new Dimension (100, 30);
        Dimension labelSize = new Dimension (150, 25);
        Color buttonColor = new Color(216, 223, 231);

        // Research title

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

        // Back Button

        JPanel backButtonPanel = new JPanel (new FlowLayout (FlowLayout.CENTER, 10, 10));
        JButton backButton = new JButton ("Back");
        backButton.setPreferredSize (buttonSize);
        backButton.setBackground (buttonColor);
        backButtonPanel.add (backButton);
        centerContainer.add (backButtonPanel);

        add (centerContainer, gbc);

        setVisible (true);

        browseButton.addActionListener (e -> 
        {
            JFileChooser fileChooser = new JFileChooser ();
            if (fileChooser.showOpenDialog (this) == JFileChooser.APPROVE_OPTION)
            {
                fileField.setText (fileChooser.getSelectedFile().getAbsolutePath());
                selectedFile = fileChooser.getSelectedFile();
            }
        });

        submitButton.addActionListener (e -> 
        {
            if (selectedFile == null || titleField.getText().trim().isEmpty() || abstractArea.getText().trim().isEmpty()) 
            {
                JOptionPane.showMessageDialog (this, "All fields are mandatory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try
            {
                File file = new File ("csvFiles/registrationsCSV.csv");
            
                Scanner fileReader = new Scanner(file);
                while(fileReader.hasNextLine())
                {
                    String line = fileReader.nextLine();
                    String[] parts = line.split(",");
                    if (parts[0].equals(name))
                    {
                        JOptionPane.showMessageDialog(this, "You have already registered for a presentation.", "Error", JOptionPane.ERROR_MESSAGE);
                        fileReader.close();
                        return;
                    }
                }
                fileReader.close();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error reading registration file.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            

            try
            {
                File folder = new File ("presentationMaterials");
                if (!folder.exists())
                {
                    folder.mkdir();
                }

                //nk copy file to presentationMaterials folder
                String fileName = selectedFile.getName();
                File destination = new File (folder, fileName);

                try 
                {
                    Files.copy(selectedFile.toPath(),destination.toPath());
                }
                catch(FileAlreadyExistsException exists)
                {
                    JOptionPane.showMessageDialog(this, "File already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                

                String relativePath = "presentationMaterials/" + fileName;
                try (FileWriter writer = new FileWriter ("csvFiles/registrationsCSV.csv", true))
                {
                        writer.write (name + "," +
                                    titleField.getText() + "," +
                                    supervisorField.getText() + "," +
                                    typeCombo.getSelectedItem() + "," +
                                    relativePath + "\n");

                        
                }

                JOptionPane.showMessageDialog (this, "Registration Succesful!");
                clearFields();
                selectedFile = null;

            }
         
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog (this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener (new ActionListener ()
        {
            public void actionPerformed (ActionEvent e) 
            {
                clearFields();
                cardLayout.show (cardManager, "StudentPanel");

            }
        });
        
    }

    public void clearFields() 
        {
            titleField.setText("");
            abstractArea.setText("");
            supervisorField.setText("");
            fileField.setText("");
            typeCombo.setSelectedIndex(0);
        };
}


