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
        
        JPanel centerContainer = new JPanel();
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
        //EvaluatorDashboard evaluatorPanel = new EvaluatorDashboard(cardLayout, cardManager); //commented our parts are used in other places

        //StudentDashboard studentPanel = new StudentDashboard(cardLayout, cardManager);
        //CoordinatorDashboard coordinatorPanel = new CoordinatorDashboard(cardLayout, cardManager);
        CreateSessionPage createSessionPanel = new CreateSessionPage(cardLayout, cardManager);
        GenerateSchedulePage generateSchedulePanel = new GenerateSchedulePage(cardLayout, cardManager);
        Overview overviewPanel = new Overview(cardLayout, cardManager);
        IndividualReports individualReportsPanel = new IndividualReports(cardLayout, cardManager);
        OverallSummary overallSummaryPanel = new OverallSummary(cardLayout, cardManager);
        
        //TITLE
        JLabel mainLabel = new JLabel("SEMINAR MANAGEMENT SYSTEM", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 17));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //LOGIN BUTTON
        Color buttonColor = new Color(216, 223, 231);
        Dimension buttonSize = new Dimension(100, 30);
        
        JButton goToLogin = new JButton("Login");
        goToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToLogin.setMaximumSize(buttonSize);
        goToLogin.setBackground(buttonColor);


        //REGISTER BUTTON
        JButton goToRegister = new JButton("Register");
        goToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToRegister.setMaximumSize(buttonSize);
        goToRegister.setBackground(buttonColor);

        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(mainLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(goToLogin);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(goToRegister);
        innerPanel.add(Box.createVerticalGlue());

        centerContainer.add(innerPanel, BorderLayout.CENTER);
        mainPanel.add(centerContainer, gbc);

        cardManager.add(mainPanel, "MainPanel");
        cardManager.add(loginPanel, "LoginPanel");
        cardManager.add(registerPanel, "RegisterPanel");
        //cardManager.add(evaluatorPanel, "EvaluatorPanel"); //used in other parts
        //cardManager.add(studentPanel, "StudentPanel");
       // cardManager.add(coordinatorPanel, "CoordinatorPanel");
        cardManager.add(createSessionPanel, "CreateSessionPanel");
        cardManager.add(generateSchedulePanel, "GenerateSchedulePanel");
        cardManager.add(overviewPanel, "Overview");
        cardManager.add(individualReportsPanel, "IndividualReports");
        cardManager.add(overallSummaryPanel, "OverallSummary");

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


