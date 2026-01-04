package panels;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class Register extends JPanel
{
    //public Register(CardLayout cardLayout, JPanel cardManager)
    public Register()
    {
        // Create and show the frame inside the constructor
        JFrame frame = new JFrame("Register Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        //frame.setLocationRelativeTo(null); // center on screen
        frame.setContentPane(this);        // add this panel
        frame.setVisible(true);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

      // Set pink background and make it opaque
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setBackground(Color.PINK);
        centerContainer.setOpaque(true);
        add(centerContainer, gbc);

        

        // Optional: a label to show the panel is there
        JLabel label = new JLabel("Hello, I am pink!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        centerContainer.add(label);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        centerContainer.add(registerPanel);

        
    
    }

    public static void main(String[] args) {
        new Register();
    }
}