package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class IndividualReports extends JPanel {

    private JComboBox<String> studentDropdown;

    public IndividualReports(CardLayout cardLayout, JPanel cardManager) {
        
        setBackground(new Color(245,245,245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);


        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setPreferredSize(new Dimension (600, 500));
        centerContainer.setBorder(BorderFactory.createCompoundBorder
            (
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));
        add(centerContainer, gbc);

        Color buttonColor = new Color(216, 223, 231);
        //TITLE -----------------------------------------------------------------------------------------------

        JPanel indiRepPanel = new JPanel();
        //indiRepPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        indiRepPanel.setLayout(new BoxLayout(indiRepPanel, BoxLayout.Y_AXIS));
        indiRepPanel.setBackground(Color.WHITE);
        JLabel indiRepLabel = new JLabel("INDIVIDUAL REPORTS");
        indiRepLabel.setFont(new Font("Arial", Font.BOLD, 24));
        indiRepLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerContainer.add(indiRepLabel);

        //indiRepPanel.add(indiRepLabel);

        //gbc.gridx = 0;
       // gbc.gridy = 0;
       // gbc.gridwidth = 1;
        //gbc.insets = new Insets(20, 0, 30, 0);
        // indiRepPanel.setBorder(BorderFactory.createCompoundBorder
        //     (
        //         new LineBorder(new Color(200, 200, 200), 1),
        //         BorderFactory.createEmptyBorder(20, 30, 20, 30)
        //     ));
        
        // add(indiRepPanel, gbc);


        //STUDENT ----------------------------------------------------------------------------------------------

        JPanel studentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints studentGbc = new GridBagConstraints();
        studentGbc.insets = new Insets(0, 0, 0, 10);
        //studentGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentLabel = new JLabel("Student Name:");
        studentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        studentGbc.gridx = 0;
        studentGbc.gridy = 0;
        studentPanel.add(studentLabel, studentGbc);

        studentDropdown = new JComboBox<>();
        insertStudentNames();
        studentDropdown.setPreferredSize(new Dimension(200, 25));
        studentDropdown.setBackground(buttonColor);
        studentGbc.gridx = 1;
        studentGbc.gridy = 0;
        //studentGbc.weightx = 1.0; // let it expand
        studentPanel.add(studentDropdown, studentGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        centerContainer.add(studentPanel);
        //add(studentPanel, gbc);


        //VIEW REPORT ------------------------------------------------------------------------------------------

        JPanel reportButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton viewReportButton = new JButton("View Report");
        viewReportButton.setBackground(buttonColor);

        viewReportButton.setPreferredSize(null);
        reportButtonPanel.add(viewReportButton);
        centerContainer.add(reportButtonPanel);

        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // gbc.insets = new Insets(20, 0, 20, 0);
        //add(reportButtonPanel, gbc);
        //centerContainer.add(reportButtonPanel, gbc);


        //BACK BUTTONS -----------------------------------------------------------------------------------------

        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backToOverviewButton = new JButton("Back to Overview");
        backToOverviewButton.setBackground(buttonColor);
        JButton backToDashboardButton = new JButton("Back to Dashboard");
        backToDashboardButton.setBackground(buttonColor);
        bottomButtonPanel.add(backToOverviewButton);
        bottomButtonPanel.add(backToDashboardButton);

        // gbc.gridx = 0;
        // gbc.gridy = 3;
        // gbc.insets = new Insets(20, 0, 20, 0);
        centerContainer.add(bottomButtonPanel);
        //add(bottomButtonPanel, gbc);

        
        //ACTION LISTENERS --------------------------------------------------------------------------------------

        backToOverviewButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "Overview");
                
            }
            
        });

        backToDashboardButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardManager, "CoordinatorPanel");
                
            }
            
        });

        viewReportButton.addActionListener(e -> {
            String selectedStudent = (String) studentDropdown.getSelectedItem();
            //JOptionPane.showMessageDialog(this, "Show report for: " + selectedStudent);
        });
    }


    private void insertStudentNames() {
        File file = new File("csvFiles/usersCSV.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[2].trim();
                    String role = parts[3].trim();
                    if (role.equalsIgnoreCase("Student")) {
                        studentDropdown.addItem(name);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading users CSV.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}