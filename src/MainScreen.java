import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import panels.Login;


public class MainScreen extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cardManager = new JPanel(cardLayout);

    JPanel mainPanel = new JPanel(new BorderLayout());
    Login loginPanel = new Login(cardLayout, cardManager);


    public MainScreen() {

        setTitle("Main Window");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JButton goToLogin = new JButton("Login");
        //JButton goToRegister = new JButton("Register");
        
        JLabel mainLabel = new JLabel("MAIN SCREEN", SwingConstants.CENTER);
        mainPanel.add(mainLabel, BorderLayout.CENTER);
        mainPanel.add(goToLogin, BorderLayout.SOUTH);

        cardManager.add(mainPanel, "MainPanel");
        cardManager.add(loginPanel, "LoginPanel");

       // mainPanel.add(loginPanel, "LoginPanel");
        goToLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "LoginPanel");
            }
        });


        setContentPane(cardManager);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}