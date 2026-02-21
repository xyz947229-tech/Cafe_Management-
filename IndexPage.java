import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IndexPage extends JFrame implements ActionListener {

    JButton bt1, bt2;

    IndexPage() {

        setTitle("Cafe Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== BACKGROUND PANEL =====
        BackgroundPanel bg = new BackgroundPanel("bg.png");
        bg.setLayout(new GridBagLayout()); // Center alignment
        setContentPane(bg);

        // ===== CENTER LOGIN BOX =====
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(350, 250));
        loginPanel.setBackground(new Color(0, 0, 0, 150)); // semi-transparent
        loginPanel.setLayout(new GridLayout(4, 1, 15, 15));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel admin = new JLabel("Admin Login");
        admin.setForeground(Color.WHITE);

        JLabel emp = new JLabel("Employee Login");
        emp.setForeground(Color.WHITE);

        Color brown = Color.decode("#8A7650");

        bt1 = new JButton("Login");
        bt1.setBackground(brown);
        bt1.setForeground(Color.WHITE);
        bt1.setFocusPainted(false);

        bt2 = new JButton("Login");
        bt2.setBackground(brown);
        bt2.setForeground(Color.WHITE);
        bt2.setFocusPainted(false);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        loginPanel.add(admin);
        loginPanel.add(bt1);
        loginPanel.add(emp);
        loginPanel.add(bt2);

        bg.add(loginPanel); // automatically centered

        setVisible(true);
    }

    // ===== BUTTON ACTION =====
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt1) {
            new LoginPage("Admin");
        }

        if (e.getSource() == bt2) {
            new LoginPage("Employee");
        }
    }

    public static void main(String[] args) {
        new IndexPage();
    }
}


// ===== Custom Background Panel =====
class BackgroundPanel extends JPanel {

    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}