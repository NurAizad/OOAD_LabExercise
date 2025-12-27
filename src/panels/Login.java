package panels;

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


public class Login extends JPanel
{
    public Login()
    {
        setLayout(new FlowLayout());
        setVisible(true);
    


        JLabel userLabel = new JLabel("Username:");
        add(userLabel);
        JTextField userText = new JTextField(20);
        add(userText);

        JLabel passLabel = new JLabel("Password:");
        add(passLabel);
        JPasswordField passText = new JPasswordField(20);
        add(passText);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String username = userText.getText();
                String password = new String(passText.getPassword());
                JOptionPane.showMessageDialog(null, "Username: " + username + "\nPassword: " + password);
            }
        });
    }

}