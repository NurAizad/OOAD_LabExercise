package panels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.*;

import java.awt.event.*;
import java.io.*;


public class ViewPresentation extends JPanel
{
    private String evaluatorName;
    private JTable sessionTable;
    private DefaultTableModel tableModel;

    public ViewPresentation(CardLayout cardLayout, JPanel cardManager, String evaluatorName)
    {
        this.evaluatorName = evaluatorName;

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

        //BUTTONS (IF ADA NANTI)
       // Dimension inputDim = new Dimension(300, 30);
       //tables 
        String[] columnNames = {"Student Name", "Date", "Venue", "Type", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        sessionTable = new JTable(tableModel);
        sessionTable.setRowHeight(50);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sessionTable.setRowSorter(sorter);

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING), //date
                new RowSorter.SortKey(4, SortOrder.ASCENDING) //time
            )
        );


        JScrollPane scrollPane = new JScrollPane(sessionTable);
        centerContainer.add(scrollPane);

        

        insertPresentation(evaluatorName);
    }

    public void insertPresentation(String evaluatorName)
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
                    String date = parts[2];
                    String venue = parts[3];
                    String type = parts[4];
                    String time = parts[5];

                    //displayStudentPresentation(studentName, date, venue, type, time);
                    tableModel.addRow(new Object[]{studentName, date, venue, type, time});

                }
            }
        }

        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading user file.","Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}
