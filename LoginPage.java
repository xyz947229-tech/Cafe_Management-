import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, createBtn;

    String userRole;

    // Constructor
    public LoginPage(String role) {

        this.userRole = role;

        setTitle(role + " Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        add(usernameField);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        add(passwordField);

        // Login Button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(80, 170, 100, 30);
        add(loginBtn);

        // Create Button
        createBtn = new JButton("Create Account");
        createBtn.setBounds(190, 170, 150, 30);
        add(createBtn);

        loginBtn.addActionListener(this);
        createBtn.addActionListener(this);

        setVisible(true);
    }

    // Action Method
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Debug print (check terminal)
            System.out.println("Entered Username: " + username);
            System.out.println("Entered Password: " + password);
            System.out.println("Role Expected: " + userRole);

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            try {

                Connection con = DBConnection.getConnection();

                if (con == null) {
                    JOptionPane.showMessageDialog(this, "Database Connection Failed");
                    return;
                }

                // Case-insensitive role match
                String query = "SELECT * FROM users WHERE username=? AND password=? AND LOWER(role)=LOWER(?)";

                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, userRole);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, userRole + " Login Successful");

                    dispose();  // close login page

                    // Open Admin Dashboard
                    if (userRole.equalsIgnoreCase("Admin")) {
                        new AdminDashboard();
                    }

                    // Employee login
                    else if (userRole.equalsIgnoreCase("Employee")) {
                        JOptionPane.showMessageDialog(this, "Employee Dashboard Coming Soon");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password or Role");
                }

                rs.close();
                pst.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }

        if (e.getSource() == createBtn) {
            JOptionPane.showMessageDialog(this, "Create Account Page Coming Soon");
        }
    }
}