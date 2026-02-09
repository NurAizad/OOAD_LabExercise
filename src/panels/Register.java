package panels;
import java.awt.*; 
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.*; //for file 
import java.util.Scanner;

public class Register extends JPanel
{
    public Register(CardLayout cardLayout, JPanel cardManager)
    {
        Color lightGray = new Color(245, 245, 245);
        setBackground(lightGray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
       
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout (new BoxLayout (centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder (BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        add(centerContainer, gbc);

        //IDNAME PANEL
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        idPanel.setBackground(lightGray);
        idPanel.setOpaque(true);
        centerContainer.add(idPanel, gbc);

        JLabel idLabel = new JLabel ("ID");
        idPanel.add(idLabel);
        JTextField idText = new JTextField(20);
        idPanel.add(idText);

        //PASSWORD PANEL
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passPanel.setBackground(lightGray);
        passPanel.setOpaque(true);
        centerContainer.add(passPanel, gbc);

        JLabel passLabel = new JLabel ("Password");
        passPanel.add(passLabel);
        JPasswordField passText = new JPasswordField(20);
        passPanel.add(passText);

        //NAME
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        namePanel.setBackground(lightGray);
        namePanel.setOpaque(true);
        centerContainer.add(namePanel, gbc);
        
        JLabel nameLabel = new JLabel ("Name");
        namePanel.add(nameLabel);
        JTextField nameText = new JTextField(20);
        namePanel.add(nameText);

        //ROLE PANEL
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rolePanel.setBackground(lightGray);
        rolePanel.setOpaque(true);
        centerContainer.add(rolePanel, gbc);
        
        JLabel roleLabel = new JLabel ("Role");
        rolePanel.add(roleLabel);
        String[] roles = { "Student", "Evaluator", "Coordinator" };
        JComboBox<String> roleList = new JComboBox<>(roles);
        rolePanel.add(roleList);

        //BUTTONS
        Dimension buttonSize = new Dimension(100, 30);
        Color buttonColor = new Color(216, 223, 231);
        
        //REGISTER BUTTON
        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        registerButtonPanel.setBackground(lightGray);
        centerContainer.add(registerButtonPanel);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(buttonSize);
        registerButton.setBackground(buttonColor);
        registerButtonPanel.add(registerButton);

        //BACK BUTTON
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButtonPanel.setBackground(lightGray);
        centerContainer.add(backButtonPanel);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setBackground(buttonColor);
        backButtonPanel.add(backButton);

        //ALIGN LABEL
        idLabel.setPreferredSize(new Dimension(90, 25));
        passLabel.setPreferredSize(new Dimension(90, 25));
        roleLabel.setPreferredSize(new Dimension(90, 25));
        nameLabel.setPreferredSize(new Dimension(90, 25));

        setVisible(true);

        // --- Action Listeners ---
        registerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String id = idText.getText();
                String password = new String(passText.getPassword()); //getPassword returns char
                String name = nameText.getText();
                String role = (String) roleList.getSelectedItem();

                //clear fields
                idText.setText("");
                passText.setText("");
                nameText.setText("");
                roleList.setSelectedIndex(0);
                
                File folder = new File("csvFiles");
                if (!folder.exists()) 
                    {
                        folder.mkdir(); // create folder if missing
                    }

                File file = new File("csvFiles/usersCSV.csv"); //.. means to go up one directory

                try 
                {
                    Scanner fileReader = new Scanner(file);
                    while (fileReader.hasNextLine()) 
                    {
                        String line = fileReader.nextLine();
                        String[] parts = line.split(",");
                        if (parts[0].equals(id)) 
                        {
                            JOptionPane.showMessageDialog(null, "ID already exists!","Error!", JOptionPane.ERROR_MESSAGE);
                            fileReader.close();
                            cardLayout.show(cardManager, "MainPanel"); //return to main
                            return;  // Exit the method if ID exists
                        }
                    }
                    fileReader.close();


                    FileWriter writer = new FileWriter(file, true); // true is for append mode
                    writer.write(id + "," + password + "," + name + "," + role + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    cardLayout.show(cardManager, "MainPanel");  //return to main
                    
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occured!","Error!", JOptionPane.ERROR_MESSAGE);
                }

                
            }
        });


        backButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(cardManager, "MainPanel");
            }
        });
    }
}