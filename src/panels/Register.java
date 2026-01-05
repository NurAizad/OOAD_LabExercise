package panels;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.concurrent.Flow;

public class Register extends JPanel
{
    //public Register(CardLayout cardLayout, JPanel cardManager)
    public Register()
    {
        // Create and show the frame inside the constructor
        JFrame frame = new JFrame("Register Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setContentPane(this);        // add this panel
        
        //frame.setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 10, 5);

       // Set pink background and make it opaque
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        // centerContainer.setBackground(Color.PINK);
        // centerContainer.setOpaque(true);
        add(centerContainer, gbc);


        //USERNAME PANEL
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        userPanel.setBackground(Color.CYAN);
        userPanel.setOpaque(true);
        centerContainer.add(userPanel, gbc);
        
        JLabel userLabel = new JLabel ("Username");
        userPanel.add(userLabel);
        JTextField userText = new JTextField(20);
        userPanel.add(userText);

        //PASSWORD PANEL
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        passPanel.setBackground(Color.PINK);
        passPanel.setOpaque(true);
        centerContainer.add(passPanel, gbc);

        JLabel passLabel = new JLabel ("Password");
        passPanel.add(passLabel);
        JPasswordField passText = new JPasswordField(20);
        passPanel.add(passText);

        //ROLE PANEL
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rolePanel.setBackground(Color.LIGHT_GRAY);
        rolePanel.setOpaque(true);
        centerContainer.add(rolePanel, gbc);
        
        JLabel roleLabel = new JLabel ("Role");
        rolePanel.add(roleLabel);
        String[] roles = { "Admin", "User", "Guest" };
        JComboBox<String> roleList = new JComboBox<>(roles);
        rolePanel.add(roleList);

        //ALIGN LABEL
        userLabel.setPreferredSize(new Dimension(90, 25));
        passLabel.setPreferredSize(new Dimension(90, 25));
        roleLabel.setPreferredSize(new Dimension(90, 25));

        frame.setVisible(true);
    
    }

    public static void main(String[] args) {
        new Register();
    }
}