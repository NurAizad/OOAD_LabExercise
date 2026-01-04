package panels;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class Register extends JPanel
{
    //public Register(CardLayout cardLayout, JPanel cardManager)
    public Register()
    {
        //REMOVE LATER
       
        
    

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.BLACK);
        centerContainer.setOpaque(true);
        add(centerContainer, gbc);

        //setVisible(true);

        // JFrame frame = new JFrame("Register Panel Test");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(400, 300);
        // frame.setLocationRelativeTo(null);
        // frame.add(this);
        // frame.setVisible(true);
        
        }

    public static void main(String[] args) {
        new Register();
    }
}