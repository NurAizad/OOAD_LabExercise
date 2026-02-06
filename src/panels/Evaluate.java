package panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Evaluate extends JPanel
{
    private JComboBox<String> studentNameComboBox;
    private JTextField problemClarityField;
    private JTextField methodologyField;
    private JTextField resultsField;
    private JTextField presentationField;
    private JTextArea commentsArea;
    //private JLabel totalScoreLabel;

    private JButton submitButton;
    private JButton backButton;

    //private String evaluatorName;

    public Evaluate (CardLayout cardLayout, JPanel cardManager, String evaluatorName)
    {
        //this.evaluatorName = evaluatorName;

        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createCompoundBorder
            (
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));
        add(centerContainer, gbc);

        Dimension inputDim = new Dimension(300, 30);
        
        Dimension labelDim = new Dimension(200, 25);

        JPanel studentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel studentLabel = new JLabel("Select Student: ");
        studentLabel.setPreferredSize(labelDim);
        studentPanel.add(studentLabel);

        studentNameComboBox = new JComboBox<>();
        studentNameComboBox.setPreferredSize(inputDim);
        studentPanel.add(studentNameComboBox);
        centerContainer.add(studentPanel);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel problemClarityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel problemClarityLabel = new JLabel("Problem Clarity (0-25): ");
        problemClarityLabel.setPreferredSize(labelDim);
        problemClarityPanel.add(problemClarityLabel);

        problemClarityField = new JTextField();
        problemClarityField.setPreferredSize(inputDim);
        problemClarityPanel.add(problemClarityField);
        centerContainer.add(problemClarityPanel);
        
        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel methodologyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel methodologyLabel = new JLabel("Methodology (0-25): ");
        methodologyLabel.setPreferredSize(labelDim);
        methodologyPanel.add(methodologyLabel);

        methodologyField = new JTextField();
        methodologyField.setPreferredSize(inputDim);
        methodologyPanel.add(methodologyField);
        centerContainer.add(methodologyPanel);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    
        JPanel resultsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel resultsLabel = new JLabel("Results (0-25): ");
        resultsLabel.setPreferredSize(labelDim);
        resultsPanel.add(resultsLabel);

        resultsField = new JTextField();
        resultsField.setPreferredSize(inputDim);
        resultsPanel.add(resultsField);
        centerContainer.add(resultsPanel);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel presentationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel presentationLabel = new JLabel("Presentation (0-25): ");
        presentationLabel.setPreferredSize(labelDim);
        presentationPanel.add(presentationLabel);

        presentationField = new JTextField();
        presentationField.setPreferredSize(inputDim);
        presentationPanel.add(presentationField);
        centerContainer.add(presentationPanel);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel commentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel commentsLabel = new JLabel("Comments: ");
        commentsLabel.setPreferredSize(labelDim);
        commentsPanel.add(commentsLabel);

        commentsArea = new JTextArea(5, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        commentsPanel.add(new JScrollPane(commentsArea));
        centerContainer.add(commentsPanel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        insertStudentNames(evaluatorName);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        submitButton = new JButton("Submit Evaluation");
        buttonPanel.add(submitButton);

        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        centerContainer.add(buttonPanel);


        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(cardManager, "EvaluatorPanel");
                clearFields();
            }
        });

        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    int clarity = Integer.parseInt(problemClarityField.getText().trim());
                    int methodology = Integer.parseInt(methodologyField.getText().trim());
                    int results = Integer.parseInt(resultsField.getText().trim());
                    int presentation = Integer.parseInt(presentationField.getText().trim());
                    
                    if (clarity < 0 || clarity > 25 || methodology < 0 || methodology > 25 ||
                        results < 0 || results > 25 || presentation < 0 || presentation > 25)
                        {
                            JOptionPane.showMessageDialog(null, "All scores must be between 0 and 25.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    String studentName = (String) studentNameComboBox.getSelectedItem();
                    String comments = commentsArea.getText().trim();

                    File file = new File("csvFiles/evaluationsCSV.csv");
                    try
                    {
                        if (!file.exists()) 
                        {
                            file.createNewFile(); // create file if missing
                        }

                        Scanner fileReader = new Scanner(file);
                        while (fileReader.hasNextLine())
                        {
                            String line = fileReader.nextLine();
                            String[] parts = line.split(",");
                            String existingStudentName = parts[0];
                            String existingEvaluatorName = parts[8];

                            if (existingStudentName.equals(studentName) && existingEvaluatorName.equals(evaluatorName))
                            {
                                JOptionPane.showMessageDialog(null, "You have already evaluated this student.","Error!", JOptionPane.ERROR_MESSAGE);
                                fileReader.close();
                                return; 
                            }
                        }
                        fileReader.close();
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, "Error creating evaluations file.","Error!", JOptionPane.ERROR_MESSAGE);
                    }


                    saveEvaluation(studentName, clarity, methodology, results, presentation, comments, evaluatorName);

                    JOptionPane.showMessageDialog(null, "Evaluation submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } 

                
                
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        

    }

    private void clearFields()
    {
            studentNameComboBox.setSelectedIndex(0); // reset selection
            problemClarityField.setText("");
            methodologyField.setText("");
            resultsField.setText("");
            presentationField.setText("");
            commentsArea.setText("");
    }

    public void insertStudentNames(String evaluatorName)
    {
        File file = new File("csvFiles/sessionsCSV.csv");
        try
        {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                String sessionEvaluator = parts[1];

                if (sessionEvaluator.equals(evaluatorName))
                {
                    String studentName = parts[0];
                    studentNameComboBox.addItem(studentName);
                }      
            }
            fileReader.close();
        }

        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading user file.","Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveEvaluation(String studentName, int clarity, int methodology, int results, int presentation, String comments, String evaluatorName)
    {
        File file = new File("csvFiles/evaluationsCSV.csv");
        int totalScore = clarity + methodology + results + presentation;

        try 
        { 
            //File file = new File("csvFiles/evaluationsCSV.csv");
            FileWriter writer = new FileWriter(file, true);
            writer.write(studentName + "," + evaluatorName + "," + clarity + "," + methodology + "," + results + "," + presentation + "," + totalScore + "," + comments.replace(",", "") + "," + evaluatorName + "\n");
            writer.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error saving evaluation.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    
}
