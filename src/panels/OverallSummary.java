package panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.List;


public class OverallSummary extends JPanel {

    public OverallSummary(CardLayout cardLayout, JPanel cardManager) {

        //WINDOW DETES ----------------------------------------------------------------------------
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        centerContainer.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        //scrollerr
        JScrollPane scrollPane = new JScrollPane(centerContainer);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(650, 500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, gbc);

        Color buttonColor = new Color(216, 223, 231);

        
        //LABELS ------------------------------------------------------------------------------------

        Map<String, Integer> reg = readRegistrationStats();
        Map<String, Object> eval = readEvaluationStats();
        List<String> peopleChoice = readPeoplesChoice();

        JLabel titleLabel = new JLabel("OVERALL SUMMARY");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerContainer.add(titleLabel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        //count
        JLabel totalStudentsLabel = makeRow("Number of students overall: ", centerContainer);
        JLabel oralCountLabel = makeRow("Oral category: ", centerContainer);
        JLabel posterCountLabel = makeRow("Poster category: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        //pie
        centerContainer.add(new PieChartPanel(reg.get("oral"), reg.get("poster")));
        centerContainer.add(Box.createRigidArea(new Dimension(0, 40)));

        //score
        JLabel highestOverallLabel = makeRow("Highest score overall: ", centerContainer);
        JLabel oralHighestLabel = makeRow("Oral category highest score: ", centerContainer);
        JLabel posterHighestLabel = makeRow("Poster category highest score: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        //award
        JLabel awardLabel = new JLabel("Award Nominations");
        awardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        awardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerContainer.add(awardLabel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel bestOralLabel = makeRow("Best Oral: ", centerContainer);
        JLabel bestPosterLabel = makeRow("Best Poster: ", centerContainer);
        JLabel peopleChoiceLabel = makeRow("People's Choice: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 25)));


        totalStudentsLabel.setText(
            "Number of students overall: " + reg.get("total")
        );
        
        oralCountLabel.setText(
            "Oral category: " + reg.get("oral")
        );

        posterCountLabel.setText(
            "Poster category: " + reg.get("poster")
        );

        highestOverallLabel.setText(
                "Highest score overall: " +
                eval.get("maxOverall") + " , " +
                String.join(", ", (List<String>) eval.get("bestOverall")) +
                " , " + eval.get("overallCategory")
        );

        oralHighestLabel.setText(
                "Oral category highest score: " +
                eval.get("maxOral") + " , " +
                String.join(", ", (List<String>) eval.get("bestOral"))
        );

        posterHighestLabel.setText(
                "Poster category highest score: " +
                eval.get("maxPoster") + " , " +
                String.join(", ", (List<String>) eval.get("bestPoster"))
        );

        bestOralLabel.setText(
                "Best Oral: " +
                String.join(", ", (List<String>) eval.get("bestOral"))
        );

        bestPosterLabel.setText(
                "Best Poster: " +
                String.join(", ", (List<String>) eval.get("bestPoster"))
        );

        peopleChoiceLabel.setText(
            "People's Choice: " + String.join(", ", peopleChoice)
        );


        //BUTTONS ---------------------------------------------------------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton exportButton = new JButton("Export Summary");
        exportButton.setBackground(buttonColor);
        exportButton.setPreferredSize(new Dimension(160, 30));
        buttonPanel.add(exportButton);

        JButton backToOverviewButton = new JButton("Back to Overview");
        backToOverviewButton.setBackground(buttonColor);
        backToOverviewButton.setPreferredSize(new Dimension(160, 30));

        JButton backToDashboardButton = new JButton("Back to Dashboard");
        backToDashboardButton.setBackground(buttonColor);
        backToDashboardButton.setPreferredSize(new Dimension(160, 30));

        buttonPanel.add(backToOverviewButton);
        buttonPanel.add(backToDashboardButton);

        centerContainer.add(buttonPanel);


        //ACTION LISTENERS -------------------------------------------------------------------------------

        
        exportButton.addActionListener(e -> exportOverall());

        backToOverviewButton.addActionListener(e ->
                cardLayout.show(cardManager, "Overview")
        );

        backToDashboardButton.addActionListener(e ->
                cardLayout.show(cardManager, "CoordinatorPanel")
        );
    }


    //FUNCTIONS -------------------------------------------------------------------------------------------

    private JLabel makeRow(String text, JPanel parent) 
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(500, 22));
        row.add(label);
        parent.add(row);
        return label;
    }


    private Map<String, Integer> readRegistrationStats() 
    {

        int total = 0;
        int oral = 0;
        int poster = 0;

        try (Scanner sc = new Scanner(new File("csvFiles/registrationsCSV.csv"))) {

            while (sc.hasNextLine()) 
            {
                String[] parts = sc.nextLine().split(",");
                if (parts.length < 5) continue;

                total++;
                String type = parts[4].trim();

                if (type.equalsIgnoreCase("Oral")) oral++;
                else if (type.equalsIgnoreCase("Poster")) poster++;
            }
        } 
        
        catch (Exception e) {}

        Map<String, Integer> map = new HashMap<>();
        map.put("total", total);
        map.put("oral", oral);
        map.put("poster", poster);
        return map;
    }


    private Map<String, Object> readEvaluationStats() 
    {

        int maxOverall = -1;
        List<String> bestOverall = new ArrayList<>();
        String overallCategory = "";

        int maxOral = -1;
        List<String> bestOral = new ArrayList<>();

        int maxPoster = -1;
        List<String> bestPoster = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("csvFiles/evaluationsCSV.csv"))) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");
                if (p.length < 9) continue;

                String name = p[0].trim();
                int totalScore = Integer.parseInt(p[6].trim());
                String type = p[8].trim();

                //ovverall
                if (totalScore > maxOverall) 
                {
                    maxOverall = totalScore;
                    bestOverall.clear();
                    bestOverall.add(name);
                    overallCategory = type;
                } 
                
                else if (totalScore == maxOverall) 
                {
                    bestOverall.add(name);
                }

                //oral
                if (type.equalsIgnoreCase("Oral"))
                {
                    if (totalScore > maxOral) 
                    {
                        maxOral = totalScore;
                        bestOral.clear();
                        bestOral.add(name);
                    } 
                    
                    else if (totalScore == maxOral) 
                    {
                        bestOral.add(name);
                    }
                }

                //poster
                if (type.equalsIgnoreCase("Poster")) 
                {
                    if (totalScore > maxPoster) 
                    {
                        maxPoster = totalScore;
                        bestPoster.clear();
                        bestPoster.add(name);
                    } 
                    
                    else if (totalScore == maxPoster) 
                    {
                        bestPoster.add(name);
                    }
                }
            }
        } 
        
        catch (Exception e) {}

        Map<String, Object> map = new HashMap<>();
        map.put("maxOverall", maxOverall);
        map.put("bestOverall", bestOverall);
        map.put("overallCategory", overallCategory);
        map.put("maxOral", maxOral);
        map.put("bestOral", bestOral);
        map.put("maxPoster", maxPoster);
        map.put("bestPoster", bestPoster);

        return map;
    }

    private List<String> readPeoplesChoice() {
        int maxVotes = -1;
        List<String> winners = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("csvFiles/choiceCSV.csv"))) {

            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");
                if (p.length < 2) continue;

                String name = p[0].trim();
                int votes = Integer.parseInt(p[1].trim());

                if (votes > maxVotes) 
                {
                    maxVotes = votes;
                    winners.clear();
                    winners.add(name);
                } 
                
                else if (votes == maxVotes) 
                {
                    winners.add(name);
                }
            }
        } 
        
        catch (Exception e) {}

        return winners;
    }


    private void exportOverall() {

        Map<String, Integer> reg = readRegistrationStats();
        Map<String, Object> eval = readEvaluationStats();
        List<String> peopleChoice = readPeoplesChoice();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose export location");
        chooser.setSelectedFile(new File("OverallSummary.txt"));

        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return; // user cancelled
        }

        File file = chooser.getSelectedFile();

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("=========================\n");
            writer.write("OVERALL SUMMARY REPORT\n");
            writer.write("=========================\n\n");

            writer.write("NUMBER OF STUDENTS\n");
            writer.write("Total: " + reg.get("total") + "\n");
            writer.write("Oral: " + reg.get("oral") + "\n");
            writer.write("Poster: " + reg.get("poster") + "\n\n");

            writer.write("HIGHEST SCORES\n");
            writer.write("Overall: " + eval.get("maxOverall") + " , " +
                    String.join(", ", (List<String>) eval.get("bestOverall")) +
                    " , " + eval.get("overallCategory") + "\n");
            writer.write("Oral: " + eval.get("maxOral") + " , " +
                    String.join(", ", (List<String>) eval.get("bestOral")) + "\n");
            writer.write("Poster: " + eval.get("maxPoster") + " , " +
                    String.join(", ", (List<String>) eval.get("bestPoster")) + "\n\n");

            writer.write("AWARD NOMINATIONS\n");
            writer.write("Best Oral: " + String.join(", ", (List<String>) eval.get("bestOral")) + "\n");
            writer.write("Best Poster: " + String.join(", ", (List<String>) eval.get("bestPoster")) + "\n");
            writer.write("People's Choice: " + String.join(", ", peopleChoice) + "\n");

            JOptionPane.showMessageDialog(
                    this,
                    "Overall summary exported successfully.",
                    "Export Complete",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to export overall summary.",
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }


}
