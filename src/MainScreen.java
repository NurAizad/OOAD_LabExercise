import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import panels.*;

public class MainScreen extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cardManager = new JPanel(cardLayout);

    public MainScreen() {

        setTitle("Main Window");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //PANELS
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Login loginPanel = new Login(cardLayout, cardManager);
        Register registerPanel = new Register(cardLayout, cardManager);
        EvaluatorDashboard evaluatorPanel = new EvaluatorDashboard(cardLayout, cardManager);
        
        //TITLE
        JLabel mainLabel = new JLabel("MAIN SCREEN", SwingConstants.CENTER);
        mainLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mainLabel);
        //mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(Box.createVerticalGlue());
        //mainPanel.add(mainLabel, BorderLayout.CENTER);
        //mainPanel.add(goToLogin, BorderLayout.SOUTH);

        //LOGIN BUTTON
        Dimension buttonSize = new Dimension(100, 30);
        
        JButton goToLogin = new JButton("Login");
        goToLogin.setAlignmentX(CENTER_ALIGNMENT);
        goToLogin.setMaximumSize(buttonSize);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(goToLogin);


        //REGISTER BUTTON
        JButton goToRegister = new JButton("Register");
        goToRegister.setAlignmentX(CENTER_ALIGNMENT);
        goToRegister.setMaximumSize(buttonSize);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(goToRegister);

        mainPanel.add(Box.createVerticalGlue());

        cardManager.add(mainPanel, "MainPanel");
        cardManager.add(loginPanel, "LoginPanel");
        cardManager.add(registerPanel, "RegisterPanel");
        cardManager.add(evaluatorPanel, "EvaluatorPanel");

        goToLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "LoginPanel");
            }
        });

        goToRegister.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "RegisterPanel");
            }
        });


        setContentPane(cardManager);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}