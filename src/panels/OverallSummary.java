package panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.File;
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

        JLabel titleLabel = new JLabel("OVERALL SUMMARY");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerContainer.add(titleLabel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel totalStudentsLabel = makeRow("Number of students overall: ", centerContainer);
        JLabel oralCountLabel = makeRow("Oral category: ", centerContainer);
        JLabel posterCountLabel = makeRow("Poster category: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel highestOverallLabel = makeRow("Highest score overall: ", centerContainer);
        JLabel oralHighestLabel = makeRow("Oral category highest score: ", centerContainer);
        JLabel posterHighestLabel = makeRow("Poster category highest score: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 25)));


        JLabel awardLabel = new JLabel("Award Nominations");
        awardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        awardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerContainer.add(awardLabel);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel bestOralLabel = makeRow("Best Oral: ", centerContainer);
        JLabel bestPosterLabel = makeRow("Best Poster: ", centerContainer);
        JLabel peopleChoiceLabel = makeRow("People's Choice: ", centerContainer);
        centerContainer.add(Box.createRigidArea(new Dimension(0, 25)));


        //BUTTONS ---------------------------------------------------------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton backToOverviewButton = new JButton("Back to Overview");
        backToOverviewButton.setBackground(buttonColor);
        backToOverviewButton.setPreferredSize(new Dimension(180, 30));

        JButton backToDashboardButton = new JButton("Back to Dashboard");
        backToDashboardButton.setBackground(buttonColor);
        backToDashboardButton.setPreferredSize(new Dimension(180, 30));

        buttonPanel.add(backToOverviewButton);
        buttonPanel.add(backToDashboardButton);

        centerContainer.add(buttonPanel);

        Map<String, Integer> reg = readRegistrationStats();
        Map<String, Object> eval = readEvaluationStats();
        List<String> peopleChoice = readPeoplesChoice();

        totalStudentsLabel.setText(
                "Number of students overall: " + reg.get("total")
        );

        oralCountLabel.setText(
                "Category 1 (Oral): " + reg.get("oral")
        );

        posterCountLabel.setText(
                "Category 2 (Poster): " + reg.get("poster")
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


        //ACTION LISTENERS -------------------------------------------------------------------------------

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



}
