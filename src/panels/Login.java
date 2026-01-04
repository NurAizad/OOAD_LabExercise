package panels;

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


public class Login extends JPanel
{
    public Login(CardLayout cardLayout, JPanel cardManager)
    {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;


        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        
        add(centerContainer, gbc);

    

        //username label and text field
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(userPanel);

        JLabel userLabel = new JLabel("Username:");
        userPanel.add(userLabel);
        JTextField userText = new JTextField(20);
        userPanel.add(userText);

        //password label and text field
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(passPanel);

        JLabel passLabel = new JLabel("Password:");
        passPanel.add(passLabel);
        JPasswordField passText = new JPasswordField(20);
        passPanel.add(passText);

        //BUTTONS
        Dimension buttonSize = new Dimension(100, 30);

        //LOGIN BUTTON
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(loginButtonPanel);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonSize);
        loginButtonPanel.add(loginButton);
        
        //BACK BUTTON
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(backButtonPanel);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButtonPanel.add(backButton);
        setVisible(true);

        // --- Action Listeners ---
        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String username = userText.getText();
                String password = new String(passText.getPassword());
                JOptionPane.showMessageDialog(null, "Username: " + username + "\nPassword: " + password);
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