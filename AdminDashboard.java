import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminDashboard extends JFrame {

    JTextField monthField;
    JButton searchBtn;
    JTextArea displayArea;

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Enter Month:"));
        monthField = new JTextField(10);
        topPanel.add(monthField);

        searchBtn = new JButton("Search");
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> loadEmployeeData());

        setVisible(true);
    }

    private void loadEmployeeData() {

        String month = monthField.getText();
        displayArea.setText("");

        try {
            Connection con = DBConnection.getConnection();

            String query =
                "SELECT e.name, e.phone, e.whatsapp, e.email, e.instagram, e.salary, " +
                "a.absent_days, a.reason, s.paid_status " +
                "FROM employees e " +
                "LEFT JOIN attendance a ON e.id = a.emp_id AND a.month=? " +
                "LEFT JOIN salary s ON e.id = s.emp_id AND s.month=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, month);
            pst.setString(2, month);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                displayArea.append("=====================================\n");
                displayArea.append("Name: " + rs.getString("name") + "\n");
                displayArea.append("Phone: " + rs.getString("phone") + "\n");
                displayArea.append("WhatsApp: " + rs.getString("whatsapp") + "\n");
                displayArea.append("Email: " + rs.getString("email") + "\n");
                displayArea.append("Instagram: " + rs.getString("instagram") + "\n");
                displayArea.append("Salary: " + rs.getDouble("salary") + "\n");
                displayArea.append("Absent Days: " + rs.getInt("absent_days") + "\n");
                displayArea.append("Leave Reason: " + rs.getString("reason") + "\n");
                displayArea.append("Salary Paid: " + rs.getString("paid_status") + "\n");
                displayArea.append("=====================================\n\n");
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}