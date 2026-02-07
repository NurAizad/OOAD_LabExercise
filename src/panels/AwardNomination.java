package panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AwardNomination extends JFrame {

    private String currentUsername;

    public AwardNomination(String username) {
        this.currentUsername = username;

        setTitle("Award Nomination");
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        // --- Center Container ---
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        add(centerContainer, gbc);

        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.insets = new Insets(10, 10, 10, 10);
        innerGbc.fill = GridBagConstraints.HORIZONTAL;

        Color buttonColor = new Color(216, 223, 231);

        // --- Title ---
        JLabel titleLabel = new JLabel("People's Choice Award Nomination");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        innerGbc.gridwidth = 2;
        centerContainer.add(titleLabel, innerGbc);

        // --- Student Label ---
        JLabel studentLabel = new JLabel("Student Name:");
        innerGbc.gridx = 0;
        innerGbc.gridy = 1;
        innerGbc.gridwidth = 1;
        centerContainer.add(studentLabel, innerGbc);

        // --- Student Dropdown ---
        JComboBox<String> studentDropdown = new JComboBox<>();
        insertStudentNames(studentDropdown);
        studentDropdown.setPreferredSize(new Dimension(200, 25));
        innerGbc.gridx = 1;
        innerGbc.gridy = 1;
        centerContainer.add(studentDropdown, innerGbc);

        // --- Submit Button ---
        JButton submitButton = new JButton("Submit Nomination");
        submitButton.setBackground(buttonColor);
        innerGbc.gridx = 0;
        innerGbc.gridy = 2;
        innerGbc.gridwidth = 2;
        centerContainer.add(submitButton, innerGbc);

        // --- Submit Action ---
        submitButton.addActionListener(e -> {
            String selectedName = (String) studentDropdown.getSelectedItem();
            if (selectedName == null || selectedName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Make sure you picked the right name.\nNo changes can be made.",
                    "Confirm Nomination",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                recordVote(selectedName);
                markUserVoted(currentUsername);
                JOptionPane.showMessageDialog(
                        this,
                        "Nomination saved.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });

        setVisible(true);
    }

    // --- Populate dropdown ---
    private void insertStudentNames(JComboBox<String> dropdown) {
        File file = new File("csvFiles/registrationsCSV.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String name = parts[0].trim();
                    dropdown.addItem(name);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading registrations CSV.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // --- Record vote for student ---
    private void recordVote(String name) {
        File file = new File("csvFiles/choiceCSV.csv");
        Map<String, Integer> votes = new LinkedHashMap<>();

        // Read existing votes
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 2) votes.put(parts[0], Integer.parseInt(parts[1]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Update vote count
        votes.put(name, votes.getOrDefault(name, 0) + 1);

        // Write back
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                pw.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Mark current user as voted ---
    private void markUserVoted(String username) {
        File file = new File("csvFiles/votedUsersCSV.csv");
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) { // append mode
            pw.println(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
