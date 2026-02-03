package panels;

import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;


public class Login extends JPanel
{
    public Login(CardLayout cardLayout, JPanel cardManager)
    {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;


        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        //centerContainer.setBackground(Color.PINK);
        //centerContainer.setPreferredSize(new Dimension(300, 250));
        //centerContainer.setOpaque(true);
        add(centerContainer, gbc);

    

        //username label and text field
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        idPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(idPanel);

        JLabel idLabel = new JLabel("ID");
        idPanel.add(idLabel);
        JTextField idText = new JTextField(20);
        idPanel.add(idText);

        //password label and text field
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(passPanel);

        JLabel passLabel = new JLabel("Password");
        passPanel.add(passLabel);
        JPasswordField passText = new JPasswordField(20);
        passPanel.add(passText);

        //BUTTONS
        Dimension buttonSize = new Dimension(100, 30);
        Color buttonColor = new Color(216, 223, 231);
        //LOGIN BUTTON
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        loginButtonPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(loginButtonPanel);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonSize);
        loginButton.setBackground(buttonColor);
        loginButtonPanel.add(loginButton);
        
        //BACK BUTTON
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButtonPanel.setBackground(Color.LIGHT_GRAY);
        centerContainer.add(backButtonPanel);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setBackground(buttonColor);
        backButtonPanel.add(backButton);

        //ALIGN LABELS
        idLabel.setPreferredSize(new Dimension(90, 25));
        passLabel.setPreferredSize(new Dimension(90, 25));


        setVisible(true);

        // --- Action Listeners ---
        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String id = idText.getText();
                String password = new String(passText.getPassword());

                File file = new File("csvFiles/usersCSV.csv");
               
                //clear fields after login attempt
                idText.setText("");
                passText.setText("");
               try 
                {
                    Scanner fileReader = new Scanner(file);
                    while (fileReader.hasNextLine()) 
                    {
                        String line = fileReader.nextLine();
                        String[] parts = line.split(",");
                        if (parts[0].equals(id) && parts[1].equals(password)) 
                        {
                            

                            JOptionPane.showMessageDialog(null, "Login successful!","Success!", JOptionPane.INFORMATION_MESSAGE);
                            fileReader.close();
                            //SHOW THE DASHBOARD PANEL HERE
                            String role = parts[3];

                            //IF STUDENT
                             if (role.equals("Student"))
                                {
                                    cardlayout.show (cardManager, "StudentPanel");
                                    fileReader.close();
                                    return;

                                }          
                                                 
                            //IF EVALUATOR
                            if (role.equals("Evaluator"))
                            {
                                cardLayout.show(cardManager, "EvaluatorPanel");
                                fileReader.close();
                                return;
                            }

                            //IF COORDINATOR
                            return;
                        }

                        // INCORRECT PASSWROD
                        if (parts[0].equals(id) && !parts[1].equals(password)) 
                        {
                            JOptionPane.showMessageDialog(null, "Incorrect password!","Error!", JOptionPane.ERROR_MESSAGE);
                            fileReader.close();
                            return;
                        }

                        

                        
                    }
                    fileReader.close();

                    // ID NOT FOUND
                    if (id != null) 
                    {
                        JOptionPane.showMessageDialog(null, "User not found!","Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                

                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Error reading user file.","Error!", JOptionPane.ERROR_MESSAGE);
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