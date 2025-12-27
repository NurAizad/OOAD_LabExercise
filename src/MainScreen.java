import javax.swing.*;
import java.awt.*;
import panels.LoginPanel;
package panels;


public class MainScreen extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);
    JPanel loginPanel = new JPanel();


    JButton switchToLoginButton = new JButton("Go to Login");

    public MainScreen() {

        setTitle("Main Window");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel mainLabel = new JLabel("MAIN SCREEN", SwingConstants.CENTER);
        add(mainLabel, BorderLayout.CENTER);
        add(switchToLoginButton, BorderLayout.SOUTH);

        mainPanel.add(loginPanel, "LoginPanel");
        switchToLoginButton.addActionListener(e -> 
        {
            cardLayout.show(mainPanel, "LoginPanel");
        });

         

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}