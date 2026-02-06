package panels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class Report extends JFrame{

    CardLayout cardLayout = new CardLayout();
    JPanel cardManager = new JPanel(cardLayout);

    public Report(){

        //WINDOW DETES -----------------------------------------------------------------------------------
        setTitle("Reports");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    

        //PANELS -----------------------------------------------------------------------------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        IndividualMain indiMainPanel = new IndividualMain(cardLayout, cardManager);
        IndividualReport indiPanel = new IndividualReport(cardLayout, cardManager);
        OverallMain overallPanel = new OverallMain(cardLayout, cardManager);


        //LABELS -----------------------------------------------------------------------------------------
        JLabel mainLabel = new JLabel("REPORTS", SwingConstants.CENTER);
        mainLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mainLabel);
        mainPanel.add(Box.createVerticalGlue());

        //BUTTONS ----------------------------------------------------------------------------------------

        //INDIVIDUAL REPORTS
        Color buttonColor = new Color(216, 223, 231);
        Dimension buttonSize = new Dimension(100, 30);
        
        JButton naviIndi = new JButton("Individual Reports");
        naviIndi.setAlignmentX(CENTER_ALIGNMENT);
        naviIndi.setMaximumSize(buttonSize);
        naviIndi.setBackground(buttonColor);
        mainPanel.add(naviIndi);

        //OVERALL SUMMARY
        JButton naviOverall = new JButton("Overall Summary");
        naviOverall.setAlignmentX(CENTER_ALIGNMENT);
        naviOverall.setMaximumSize(buttonSize);
        //mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        naviOverall.setBackground(buttonColor);
        mainPanel.add(naviOverall);

        //------------------------------------------------------------------------------------------------
        cardManager.add(mainPanel, "MainPanel");
        cardManager.add(indiMainPanel, "indiMainPanel");
        cardManager.add(indiPanel, "indiPanel");
        cardManager.add(overallPanel, "overallPanel");


        //NAVIGATIONS
        naviIndi.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "indiMainPanel");
            }
        });

        naviOverall.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "overallPanel");
            }
        });


        setContentPane(cardManager);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Report();
    }
}
