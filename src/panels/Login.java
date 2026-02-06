package panels;

import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Login extends JPanel
{
    public Login(CardLayout cardLayout, JPanel cardManager)
    {
        Color lightGray = new Color(245, 245, 245);
        setBackground(lightGray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        //gbc.anchor = GridBagConstraints.CENTER;


        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
       // centerContainer.setPreferredSize(new Dimension (600, 400));
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        
        add(centerContainer, gbc);

    

        //username label and text field
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        idPanel.setBackground(lightGray);
        centerContainer.add(idPanel);

        JLabel idLabel = new JLabel("ID");
        idPanel.add(idLabel);
        JTextField idText = new JTextField(20);
        idPanel.add(idText);

        //password label and text field
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passPanel.setBackground(lightGray);
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
        loginButtonPanel.setBackground(lightGray);
        centerContainer.add(loginButtonPanel);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonSize);
        loginButton.setBackground(buttonColor);
        loginButtonPanel.add(loginButton);
        
        //BACK BUTTON
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButtonPanel.setBackground(lightGray);
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
                                    String name = parts[2];
                                    StudentDashboard studentDashboard = new StudentDashboard(cardLayout, cardManager, name);
                                    cardManager.add(studentDashboard, "StudentPanel");
                                    cardLayout.show (cardManager, "StudentPanel");
                                    fileReader.close();
                                    return;

                                }          
                                                 
                            //IF EVALUATOR
                            else if (role.equals("Evaluator"))
                            {
                                //cardLayout.show(cardManager, "EvaluatorPanel");
                                String name = parts[2];
                                EvaluatorDashboard evaluatorDashboard = new EvaluatorDashboard(cardLayout, cardManager, name);
                                cardManager.add(evaluatorDashboard, "EvaluatorPanel");
                                cardLayout.show(cardManager, "EvaluatorPanel");
                                fileReader.close();
                                return;
                            }

                            //IF COORDINATOR
                            else if (role.equals("Coordinator"))
                            {
                                cardLayout.show(cardManager, "CoordinatorPanel"); 
                                fileReader.close();
                                return;
                            }
                            
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
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(cardManager, "MainPanel");
            }
        });
    }
    

}