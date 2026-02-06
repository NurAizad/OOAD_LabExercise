import javax.swing.*;
import javax.swing.border.LineBorder;

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
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
       // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JPanel centerContainer = new JPanel();
        //centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setLayout(new BorderLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setPreferredSize(new Dimension (400, 300));
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(245, 245, 245));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        Login loginPanel = new Login(cardLayout, cardManager);
        Register registerPanel = new Register(cardLayout, cardManager);
       // EvaluatorDashboard evaluatorPanel = new EvaluatorDashboard(cardLayout, cardManager);

        //StudentDashboard studentPanel = new StudentDashboard(cardLayout, cardManager);
        CoordinatorDashboard coordinatorPanel = new CoordinatorDashboard(cardLayout, cardManager);
        CreateSessionPage createSessionPanel = new CreateSessionPage(cardLayout, cardManager);
        OverviewPanel overviewPanel = new OverviewPanel(cardLayout, cardManager);

        
        //TITLE
        JLabel mainLabel = new JLabel("MAIN SCREEN", SwingConstants.CENTER);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //innerPanel.add(Box.createVerticalGlue());
        //innerPanel.add(mainLabel);
        //innerPanel.add(Box.createRigidArea(new Dimension(0, 100)));
       // centerContainer.add(Box.createVerticalGlue());

        //LOGIN BUTTON
        Color buttonColor = new Color(216, 223, 231);
        Dimension buttonSize = new Dimension(100, 30);
        
        JButton goToLogin = new JButton("Login");
        goToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToLogin.setMaximumSize(buttonSize);
        //mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        goToLogin.setBackground(buttonColor);
        //innerPanel.add(goToLogin);
        //innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        //REGISTER BUTTON
        JButton goToRegister = new JButton("Register");
        goToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToRegister.setMaximumSize(buttonSize);
        //innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        goToRegister.setBackground(buttonColor);
        //innerPanel.add(goToRegister);
        //innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(mainLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(goToLogin);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(goToRegister);
        innerPanel.add(Box.createVerticalGlue());

        centerContainer.add(innerPanel, BorderLayout.CENTER);
        //centerContainer.add(Box.createVerticalGlue());
        mainPanel.add(centerContainer, gbc);

        cardManager.add(mainPanel, "MainPanel");
        cardManager.add(loginPanel, "LoginPanel");
        cardManager.add(registerPanel, "RegisterPanel");
        //cardManager.add(evaluatorPanel, "EvaluatorPanel");
        //cardManager.add(studentPanel, "StudentPanel");
        cardManager.add(coordinatorPanel, "CoordinatorPanel");
        cardManager.add(createSessionPanel, "CreateSessionPanel");
        cardManager.add(overviewPanel, "OverviewPanel");

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


