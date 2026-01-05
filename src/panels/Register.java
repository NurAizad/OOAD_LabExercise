package panels;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class Register extends JPanel
{
    public Register(CardLayout cardLayout, JPanel cardManager)
    //public Register()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
       
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        add(centerContainer, gbc);

        //IDNAME PANEL
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        idPanel.setBackground(Color.CYAN);
        idPanel.setOpaque(true);
        centerContainer.add(idPanel, gbc);

        JLabel idLabel = new JLabel ("ID");
        idPanel.add(idLabel);
        JTextField idText = new JTextField(20);
        idPanel.add(idText);

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

        //NAME
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        namePanel.setBackground(Color.ORANGE);
        namePanel.setOpaque(true);
        centerContainer.add(namePanel, gbc);
        
        JLabel nameLabel = new JLabel ("Name");
        namePanel.add(nameLabel);
        JTextField nameText = new JTextField(20);
        namePanel.add(nameText);

        //ROLE PANEL
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rolePanel.setBackground(Color.LIGHT_GRAY);
        rolePanel.setOpaque(true);
        centerContainer.add(rolePanel, gbc);
        
        JLabel roleLabel = new JLabel ("Role");
        rolePanel.add(roleLabel);
        String[] roles = { "Student", "Evaluator", "Coordinator" };
        JComboBox<String> roleList = new JComboBox<>(roles);
        rolePanel.add(roleList);

        //BUTTONS
        Dimension buttonSize = new Dimension(100, 30);
        //REGISTER BUTTON
        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(registerButtonPanel, gbc);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(buttonSize);
        registerButtonPanel.add(registerButton);

        //BACK BUTTON
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerContainer.add(backButtonPanel, gbc);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButtonPanel.add(backButton);

        //ALIGN LABEL
        idLabel.setPreferredSize(new Dimension(90, 25));
        passLabel.setPreferredSize(new Dimension(90, 25));
        roleLabel.setPreferredSize(new Dimension(90, 25));
        nameLabel.setPreferredSize(new Dimension(90, 25));

        setVisible(true);

        // --- Action Listeners ---
        registerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String id = idText.getText();
                String password = new String(passText.getPassword());
                String name = nameText.getText();
                String role = (String) roleList.getSelectedItem();
                JOptionPane.showMessageDialog(null, "ID: " + id + "\nPassword: " + password + "\nName: " + name + "\nRole: " + role);
            }
        });

        backButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(cardManager, "MainPanel");
            }
        });
    }
}