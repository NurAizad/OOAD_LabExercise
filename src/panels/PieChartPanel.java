package panels;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {
    private int oralCount;
    private int posterCount;

    public PieChartPanel(int oralCount, int posterCount) {
        this.oralCount = oralCount;
        this.posterCount = posterCount;
        setPreferredSize(new Dimension(550, 250));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int total = oralCount + posterCount;
        if (total == 0) return;

        //angle calculations
        int startAngle = 0;
        int oralAngle = (int) Math.round(360.0 * oralCount / total);
        int posterAngle = 360 - oralAngle;

        //percentage
        double percentOral = ((double) oralCount / total) * 100;
        double percentPoster = ((double) posterCount / total) * 100;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //oral
        g2.setColor(new Color(232, 72, 85));
        g2.fillArc(100, 50, 200, 200, startAngle, oralAngle);

        //poster
        g2.setColor(new Color(131, 182, 146));
        g2.fillArc(100, 50, 200, 200, startAngle + oralAngle, posterAngle);

        //colours
        g2.setColor(Color.BLACK);
        g2.drawString("Oral: " + oralCount + " (" + percentOral + "%)", 320, 100);
        g2.drawString("Poster: " + posterCount+ " (" + percentPoster + "%)", 320, 130);
    }
}
