package panels;

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


public class Login extends JPanel
{
    public Login()
    {
        //setTitle("Login");
        //setSize(900, 600);

        //JPanel panel = new JPanel(new FlowLayout());
        setLayout(new FlowLayout());
        setVisible(true);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the close, minimise button
        //panel.setResizeable(false); 

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

    public static void main(String[] args) 
    {
        new Login();
    }
}