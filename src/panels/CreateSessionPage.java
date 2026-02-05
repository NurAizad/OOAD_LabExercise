package panels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class CreateSessionPage extends JPanel{
    private CardLayout cardLayout;
    private JPanel cardManager;
    private void loadUsersByRole(JComboBox<String> comboBox, String targetRole) {
    File file = new File("csvFiles/usersCSV.csv");
    if (!file.exists()) return;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            // Assuming CSV format: Username,Password,Role
            if (data.length >= 4) {
                String name = data[2].trim();
                String role = data[3].trim();
                
                if (role.equalsIgnoreCase(targetRole)) {
                    comboBox.addItem(name);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    private void makeSearchable(JComboBox<String> comboBox, String[] originalItems) {
    comboBox.setEditable(true);
    JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();

    

    textField.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            String currentText = textField.getText();
            if (currentText.startsWith("-- Select")) {
                textField.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().isEmpty()) {
                // Determine which placeholder to put back
                if (comboBox.getToolTipText() != null && comboBox.getToolTipText().contains("Evaluator")) {
                    textField.setText("-- Select an evaluator --");
                } else {
                    textField.setText("-- Select a student --");
                }
            }
        }
    });
    textField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            // Ignore navigation keys so user can still select with arrows
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || 
                e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                return;
            }

            String filter = textField.getText();
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
            model.removeAllElements();

            // Loop through original data and add matches to the dropdown
            for (String item : originalItems) {
                if (item.toLowerCase().contains(filter.toLowerCase())) {
                    model.addElement(item);
                }
            }

            comboBox.setPopupVisible(model.getSize() > 0);
            textField.setText(filter); // Keep the cursor/text consistent
        }
    });
}
    
    public CreateSessionPage(CardLayout cardLayout, JPanel cardManager) {

        this.cardLayout = cardLayout;
        this.cardManager = cardManager;

        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        //gbc.anchor = GridBagConstraints.CENTER;

        JPanel mainCard = new JPanel();
        mainCard.setLayout(new BoxLayout(mainCard, BoxLayout.Y_AXIS));
        mainCard.setBackground(Color.WHITE);
        mainCard.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        Dimension inputDim = new Dimension(300, 30);

        String[] types = {"-- Select a session type --","Oral", "Poster"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        typeCombo.setPreferredSize(inputDim);
        //container.add(createInputRow("Type", typeCombo));

        String[] studentPlaceholder = {"-- Select a student --"};
        JComboBox<String> studentList = new JComboBox<>(new DefaultComboBoxModel<>(studentPlaceholder));
        studentList.setPreferredSize(inputDim);
        loadUsersByRole(studentList, "Student");
        
        String[] allStudents = new String[studentList.getItemCount()];
        for (int i = 0; i < studentList.getItemCount(); i++) {
            allStudents[i] = studentList.getItemAt(i);
        }
        makeSearchable(studentList, allStudents);
        //studentList.setEditable(true);
        //container.add(createInputRow("Type", typeCombo));

        studentList.addActionListener(e -> {
            String selectedStudent = (String) studentList.getSelectedItem();
            // Skip if nothing is selected or if it's the placeholder
            if (selectedStudent == null || selectedStudent.startsWith("-- Select")) return;

            try (BufferedReader br = new BufferedReader(new FileReader("csvFiles/registrationsCSV.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    // registrationsCSV format: Name(0), Topic(1), Description(2), Type(3)
                    if (data.length >= 4 && data[0].trim().equalsIgnoreCase(selectedStudent)) {
                        String registeredType = data[3].trim();
                        
                        // Set typeCombo to match the CSV value
                        for (int i = 0; i < typeCombo.getItemCount(); i++) {
                            if (typeCombo.getItemAt(i).equalsIgnoreCase(registeredType)) {
                                typeCombo.setSelectedIndex(i);
                                break;
                            }
                        }
                        break; 
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        

        String[] evaluatorPlaceholder = {"-- Select an evaluator --"};
        JComboBox<String> evaluatorList = new JComboBox<>(new DefaultComboBoxModel<>(evaluatorPlaceholder));
        evaluatorList.setPreferredSize(inputDim);
        loadUsersByRole(evaluatorList, "Evaluator");

        String[] allEvaluators = new String[evaluatorList.getItemCount()];
        for (int i = 0; i < evaluatorList.getItemCount(); i++) {
            allEvaluators[i] = evaluatorList.getItemAt(i);
        }
        makeSearchable(evaluatorList, allEvaluators);
        //evaluatorList.setEditable(true);
        //container.add(createInputRow("Type", typeCombo));


        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setPreferredSize(inputDim);


        String[] venues = {"-- Select a venue --", "Hall A", "Lecture Theater 1", "Computer Lab 4", "Conference Room", "Virtual Link (Zoom/Teams)" };
        JComboBox<String> venueList = new JComboBox<>(venues);
        venueList.setPreferredSize(inputDim);

        String[] times = {"-- Select a time slot --", "09:00 - 10:00", "10:00 - 11:00", "14:00 - 15:00", "16:00 - 17:00" };
        JComboBox<String> timeList = new JComboBox<>(times);
        timeList.setPreferredSize(inputDim);

        
        mainCard.add(createStyledRow("Select Student:", studentList, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10))); 
        mainCard.add(createStyledRow("Select Evaluator:", evaluatorList, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10))); 
        mainCard.add(createStyledRow("Session Type:", typeCombo, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10)));
        mainCard.add(createStyledRow("Select Date:", dateSpinner, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10))); 
        mainCard.add(createStyledRow("Select Venue:", venueList, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10)));
        mainCard.add(createStyledRow("Time Slot:", timeList, 120));
        mainCard.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton saveButton = new JButton("Create Session");
        saveButton.setPreferredSize(new Dimension(180, 40));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCard.add(saveButton);

        gbc.gridy = 0;
        add(mainCard, gbc);

        //gbc.gridy = 1;
        //gbc.insets = new Insets(10, 0, 0, 0);
        
        //add(saveButton, gbc);

        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        

        //buttonPanel.add(saveButton);
        //mainCard.add(buttonPanel);
        

        saveButton.addActionListener(e -> {
            String student = (String) studentList.getSelectedItem();
            String evaluator = (String) evaluatorList.getSelectedItem();
            String type = (String) typeCombo.getSelectedItem();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String dateStr = sdf.format(dateSpinner.getValue());
            String venue = (String) venueList.getSelectedItem();
            String time = (String) timeList.getSelectedItem();

            if (studentList.getSelectedIndex() == 0 || evaluatorList.getSelectedIndex() == 0 || typeCombo.getSelectedIndex() == 0 || venueList.getSelectedIndex() == 0 || timeList.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Conflict Validation
            boolean isRegistered = false;
            try (BufferedReader regReader = new BufferedReader(new FileReader("csvFiles/registrationsCSV.csv"))) {
                String line;
                while ((line = regReader.readLine()) != null) {
                    if (line.startsWith(student + ",")) {
                        isRegistered = true;
                        break;
                    }
                }
            } catch (IOException ex) { ex.printStackTrace(); }

            if (!isRegistered) {
                JOptionPane.showMessageDialog(this, "This student has not registered for the seminar yet.");
                return;
            }

            File sessionFile = new File("csvFiles/sessionsCSV.csv");
            if (sessionFile.exists()) {
                try (BufferedReader sessionReader = new BufferedReader(new FileReader(sessionFile))) {
                    String line;
                    while ((line = sessionReader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length < 7) continue;

                        String exStudent = parts[0].trim();
                        String exEvaluator = parts[1].trim();
                        String exDate = parts[3].trim();
                        String exVenue = parts[4].trim();
                        String exTime = parts[6].trim();

                        if (student.equals(exStudent)) {
                            JOptionPane.showMessageDialog(this, "This student is already assigned to a session.");
                            return;
                        }

                        if (dateStr.equals(exDate) && time.equals(exTime)) {
                            if (evaluator.equalsIgnoreCase(exEvaluator)) {
                                JOptionPane.showMessageDialog(this, "Evaluator is not available at this time.");
                                return;
                            }
                            if (venue.equals(exVenue)) {
                                JOptionPane.showMessageDialog(this, "Venue is already booked for this time!");
                                return;
                            }
                        }
                    }
                } catch (IOException ex) { ex.printStackTrace(); }
            }


            try {
                File folder = new File("csvFiles");
                if (!folder.exists()) folder.mkdir();

                File file = new File("csvFiles/sessionsCSV.csv");
                FileWriter writer = new FileWriter(file, true);

                writer.write(student + "," + evaluator + "," + type + "," + dateStr + "," + venue + ",," + time + "\n");
                writer.close();

                JOptionPane.showMessageDialog(this, "Session Created Successfully.");
                cardLayout.show(cardManager, "CoordinatorPanel");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    
    
    }
    private JPanel createStyledRow(String labelName, JComponent inputField, int labelWidth) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel label = new JLabel(labelName);
        label.setPreferredSize(new Dimension(labelWidth, 25));
        //panel.setOpaque(false);
        panel.add(label);
        panel.add(inputField);
        return panel;
    }
    // Temporary main method for independent testing
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Test CreateSessionPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // CardLayout and JPanel are required by your constructor
        CardLayout cl = new CardLayout();
        JPanel container = new JPanel(cl);

        // Create the page and add it to the container
        CreateSessionPage page = new CreateSessionPage(cl, container);
        container.add(page, "Test");

        frame.add(container);
        frame.setVisible(true);
    } */
}
