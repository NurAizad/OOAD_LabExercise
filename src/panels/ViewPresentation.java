package panels;
import java.awt.*;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

import java.util.*;

import java.awt.event.*;
import java.io.*;


public class ViewPresentation extends JPanel
{
    private JTable sessionTable;
    private DefaultTableModel tableModel;


    public ViewPresentation(CardLayout cardLayout, JPanel cardManager, String evaluatorName)
    {

        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.WHITE);
        //centerContainer.setPreferredSize(new Dimension (600, 400));
        centerContainer.setBorder(BorderFactory.createCompoundBorder
            (
                new LineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
            ));
        add(centerContainer, gbc);

        //BUTTONS (IF ADA NANTI)
       // Dimension inputDim = new Dimension(300, 30);
       //tables 
        String[] columnNames = {"Student Name", "Date", "Venue", "Type", "Time", "View"};
        tableModel = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        sessionTable = new JTable(tableModel);
        sessionTable.setRowHeight(50);
        sessionTable.getColumnModel().getColumn(2).setPreferredWidth(125); //venue resize
        sessionTable.getColumnModel().getColumn(0).setPreferredWidth(80); //name resize

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

        //back button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(100, 30));
        backButton.setBackground(new Color(216, 223, 231));
        centerContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        centerContainer.add(backButton);

        backButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(cardManager, "EvaluatorPanel");
            }
        });
        

        //mouse listener to open presentaiton
        sessionTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int row = sessionTable.rowAtPoint(e.getPoint());
                int col = sessionTable.columnAtPoint(e.getPoint());
                String studentName = (String) sessionTable.getValueAt(row, 0); //make sure to cahnge to string

                if (col == 5) //view presentation
                {    
                    viewPresentation(studentName);
                }
            }
        });

        // change to hand when hovering
        sessionTable.addMouseMotionListener(new MouseMotionAdapter() 
        {
            @Override
            public void mouseMoved(MouseEvent e) 
            {
                int col = sessionTable.columnAtPoint(e.getPoint());
                if (col == sessionTable.getColumnCount() - 1 ) 
                {
                    sessionTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } 
                else 
                {
                    sessionTable.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
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
                    tableModel.addRow(new Object[]{studentName, date, venue, type, time, "Click to View"});

                }
            }
            fileReader.close();
        }

        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading user file.","Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewPresentation(String studentName)
    {
        File file = new File ("csvFiles/registrationsCSV.csv");
        try
        {
            Scanner fileReader = new Scanner (file);
            while (fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                String RegisteredStudentName = parts[0];
                String presentationFilePath = parts[4];

                if (RegisteredStudentName.equals(studentName))
                {
                    // Open the presentation file
                    File presentationFile = new File(presentationFilePath);
                    if (presentationFile.exists())
                    {
                        Desktop.getDesktop().open(presentationFile);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Presentation file not found.","Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
            fileReader.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error reading registration file.","Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

}
