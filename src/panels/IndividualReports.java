package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
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

        viewReportButton.addActionListener(e -> 
        {
            String selectedStudent = (String) studentDropdown.getSelectedItem();
            if (selectedStudent == null) {
                JOptionPane.showMessageDialog(this, "No student selected.");
                return;
            }

            showIndividualReportWindow(selectedStudent);

        });

    }

    //FUNCTIONS -----------------------------------------------------------------------------------------------

    //dropdown column
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

        } 
        
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Error reading users CSV.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //individual report display
    private void showIndividualReportWindow(String studentName) {

        File file2 = new File("csvFiles/evaluationsCSV.csv");
        boolean found = false;

        try (Scanner scanner = new Scanner(file2)) {
            while (scanner.hasNextLine()) {

                String[] data = scanner.nextLine().split(",", -1);

                if (data.length < 9) continue;

                if (data[0].equalsIgnoreCase(studentName)) {
                    found = true;

                    String evaluatorName = data[1];
                    String clarity = data[2];
                    String methodology = data[3];
                    String results = data[4];
                    String presentation = data[5];
                    String totalScore = data[6];
                    String comments = data[7];
                    String presentationType = data[8];

                    //dialog = pop-up window
                    JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Individual Report", Dialog.ModalityType.APPLICATION_MODAL);

                    dialog.setSize(500, 500);
                    dialog.setLocationRelativeTo(this);
                    dialog.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.insets = new Insets(5, 10, 5, 10);

                    int row = 0;

                    //TITLE ------------------------------------------------------
                    JLabel title = new JLabel("INDIVIDUAL REPORT");
                    title.setFont(new Font("Arial", Font.BOLD, 20));
                    gbc.gridy = row++;
                    gbc.anchor = GridBagConstraints.CENTER;
                    dialog.add(title, gbc);

                    gbc.anchor = GridBagConstraints.WEST;


                    //LABELS -----------------------------------------------------
                    dialog.add(new JLabel("Student Name: " + studentName), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Presentation Type: " + presentationType), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Clarity: " + clarity), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Methodology: " + methodology), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Results: " + results), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Presentation: " + presentation), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Total Score: " + totalScore), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Comments: " + comments), gbcAt(gbc, row++));
                    dialog.add(new JLabel("Evaluated By: " + evaluatorName), gbcAt(gbc, row++));


                    //BUTTONS -----------------------------------------------
                    JButton exportBtn = new JButton("Export Report");
                    gbc.gridy = row++;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(20, 10, 5, 10);
                    dialog.add(exportBtn, gbc);

                    JButton closeBtn = new JButton("Close");
                    gbc.gridy = row;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(20, 10, 10, 10);
                    dialog.add(closeBtn, gbc);

                    exportBtn.addActionListener(ev -> {

                        exportReportToTxt
                        (
                            studentName,
                            evaluatorName,
                            presentationType,
                            clarity,
                            methodology,
                            results,
                            presentation,
                            totalScore,
                            comments
                        );
                    });

                    closeBtn.addActionListener(ev -> dialog.dispose());

                    dialog.setVisible(true);
                    break;


                }
            }

        } 

        //ERRORS -------------------------------------------------------------------
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading evaluator CSV.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!found) {
            JOptionPane.showMessageDialog(
                    this,
                    "This student has not been evaluated yet.",
                    "No Report Found",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    //export txt file
    private void exportReportToTxt(String studentName, String evaluatorName, String presentationType, String clarity, String methodology, String results, String presentation, String totalScore, String comments) 
    {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose export location");
        chooser.setSelectedFile(
            new File("IndividualReport_" + studentName.replace(" ", "_") + ".txt")
        );

        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return; //user cancelled the exportation
        }

        File file = chooser.getSelectedFile();

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("=================\n");
            writer.write("INDIVIDUAL REPORT\n");
            writer.write("=================\n\n");
            writer.write("Student Name: " + studentName + "\n");
            writer.write("Presentation Type: " + presentationType + "\n\n");

            writer.write("SCORES\n");
            writer.write("Clarity: " + clarity + "\n");
            writer.write("Methodology: " + methodology + "\n");
            writer.write("Results: " + results + "\n");
            writer.write("Presentation: " + presentation + "\n");
            writer.write("Total Score: " + totalScore + "\n\n");

            writer.write("Comments: " + comments + "\n\n");

            writer.write("Evaluated By: " + evaluatorName + "\n");

            JOptionPane.showMessageDialog(this, "Report exported successfully.", "Export Complete", JOptionPane.INFORMATION_MESSAGE);

        } 
        
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Failed to export report.", "Export Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //gbc for each label in report
    private GridBagConstraints gbcAt(GridBagConstraints gbc, int y) 
    {
        GridBagConstraints copy = (GridBagConstraints) gbc.clone();
        copy.gridy = y;
        return copy;
    }


}