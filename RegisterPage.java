import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterPage extends JFrame implements ActionListener {

    JTextField tfUser;
    JPasswordField pfPass;
    JButton createBtn;
    String role;

    RegisterPage(String role) {

        this.role = role;

        setTitle("Create " + role + " Account");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Create " + role + " Account", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        tfUser = new JTextField();
        pfPass = new JPasswordField();

        createBtn = new JButton("Create Account");

        Color brown = Color.decode("#8A7650");
        createBtn.setBackground(brown);
        createBtn.setForeground(Color.WHITE);

        createBtn.addActionListener(this);

        add(title);
        add(new JLabel("New Username:"));
        add(tfUser);
        add(new JLabel("New Password:"));
        add(pfPass);
        add(createBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(this,
                "Account created (Database logic will be added later)");
    }
}