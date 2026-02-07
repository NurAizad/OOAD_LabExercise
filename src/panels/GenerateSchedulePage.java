package panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.print.*;

public class GenerateSchedulePage extends JPanel {
    //private CardLayout cardLayout;
    //private JPanel cardManager;
    private JPanel listContainer;
    private JSpinner startSpinner;
    private JSpinner endSpinner;
    private SimpleDateFormat csvDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE - dd MMMM yyyy");


    public GenerateSchedulePage(CardLayout cardLayout, JPanel cardManager) {
        //this.cardLayout = cardLayout;
        //this.cardManager = cardManager;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // --- Top Navigation Panel ---
        JPanel navPanel = new JPanel(new GridBagLayout());
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        

        // 1. Weekly Buttons
        //JButton btnPrev = new JButton("< Previous Week");
        //JButton btnNext = new JButton("Next Week >");

        Color buttonColor = new Color(216, 223, 231);
        
        // 2. Date Pickers
        startSpinner = new JSpinner(new SpinnerDateModel());
        endSpinner = new JSpinner(new SpinnerDateModel());
        formatSpinner(startSpinner);
        formatSpinner(endSpinner);

        JButton btnFilter = new JButton("Apply Range");
        btnFilter.setBackground(buttonColor);
        JButton btnPrint = new JButton("Export PDF");
        btnPrint.setBackground(buttonColor);
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(buttonColor);

        // Layout Navigation
        //gbc.gridx = 0; navPanel.add(btnPrev, gbc);
        gbc.gridx = 1; navPanel.add(new JLabel("From:"), gbc);
        gbc.gridx = 2; navPanel.add(startSpinner, gbc);
        gbc.gridx = 3; navPanel.add(new JLabel("To:"), gbc);
        gbc.gridx = 4; navPanel.add(endSpinner, gbc);
        gbc.gridx = 5; navPanel.add(btnFilter, gbc);
        gbc.gridx = 6; navPanel.add(btnPrint, gbc);
        gbc.gridx = 7; navPanel.add(btnBack, gbc);

        // --- List Container ---
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(navPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // --- Listeners ---
        // Weekly Navigation
        //btnPrev.addActionListener(e -> shiftWeek(-7));
        //btnNext.addActionListener(e -> shiftWeek(7));
        
        // Manual Filter
        btnFilter.addActionListener(e -> refreshList());
        btnPrint.addActionListener(e -> printToPDF());

        btnBack.addActionListener(e -> cardLayout.show(cardManager, "CoordinatorPanel"));

        // Initialize to current week
        setToCurrentWeek();
        refreshList();
    }

    private void formatSpinner(JSpinner spinner) {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        spinner.setPreferredSize(new Dimension(120, 25));
    }

    private void setToCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        // Snap to Monday
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            cal.add(Calendar.DATE, -1);
        }
        startSpinner.setValue(cal.getTime());
        
        // Snap to Sunday
        cal.add(Calendar.DATE, 6);
        endSpinner.setValue(cal.getTime());
    }

    /*private void shiftWeek(int days) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime((Date) startSpinner.getValue());
        calStart.add(Calendar.DATE, days);
        startSpinner.setValue(calStart.getTime());

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime((Date) endSpinner.getValue());
        calEnd.add(Calendar.DATE, days);
        endSpinner.setValue(calEnd.getTime());

        refreshList();
    }*/

    private void refreshList() {
        listContainer.removeAll();
        
        Date startDate = (Date) startSpinner.getValue();
        Date endDate = (Date) endSpinner.getValue();

        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(startDate);
        
        // Normalize time for comparison
        tempCal.set(Calendar.HOUR_OF_DAY, 0);
        tempCal.set(Calendar.MINUTE, 0);
        tempCal.set(Calendar.SECOND, 0);
        
        Calendar finalCal = Calendar.getInstance();
        finalCal.setTime(endDate);
        finalCal.set(Calendar.HOUR_OF_DAY, 23); // Include the whole end day

        // Loop through every day in the range
        while (!tempCal.after(finalCal)) {
            String dateStr = csvDateFormat.format(tempCal.getTime());
            JPanel dayPanel = createDayHeader(dayNameFormat.format(tempCal.getTime()));
            listContainer.add(dayPanel);

            loadSessionsForDate(dateStr);
            
            tempCal.add(Calendar.DATE, 1);
            listContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        listContainer.revalidate();
        listContainer.repaint();
    }

    private JPanel createDayHeader(String dayTitle) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(70, 130, 180));
        p.setMaximumSize(new Dimension(1000, 40));
        JLabel label = new JLabel(dayTitle);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setBorder(new EmptyBorder(5, 15, 5, 5));
        p.add(label, BorderLayout.WEST);
        return p;
    }

    private void loadSessionsForDate(String dateStr) {
        File file = new File("csvFiles/sessionsCSV.csv");
        if (!file.exists()) return;

        boolean hasSessions = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7 && data[3].trim().equals(dateStr)) {
                    addSessionCard(data);
                    hasSessions = true;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        if (!hasSessions) {
            JLabel none = new JLabel("  No sessions scheduled.");
            none.setFont(new Font("SansSerif", Font.ITALIC, 12));
            none.setBorder(new EmptyBorder(5, 20, 5, 0));
            listContainer.add(none);
        }
    }

    private void addSessionCard(String[] data) {
        JPanel card = new JPanel(new GridLayout(1, 4, 10, 0));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(1000, 70));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 20, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(10, 15, 10, 15)
        ));

        card.add(new JLabel("<html><b>Time:</b><br>" + data[6] + "</html>"));
        card.add(new JLabel("<html><b>Student:</b><br>" + data[0] + "</html>"));
        card.add(new JLabel("<html><b>Evaluator:</b><br>" + data[1] + "</html>"));
        card.add(new JLabel("<html><b>Venue:</b><br>" + data[4] + "</html>"));

        listContainer.add(card);
    }

    private void printToPDF() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Schedule Export");

        //ni for setting the page and how it looks on the page
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double pageWidth = pageFormat.getImageableWidth();
            double contentWidth = listContainer.getWidth();

            if (contentWidth <= 0) contentWidth = 1000;
            double scale = pageWidth / contentWidth;
            g2d.scale(scale, scale);

            //ni part yang it will render it
            listContainer.printAll(g2d);
            return Printable.PAGE_EXISTS;
        });
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}