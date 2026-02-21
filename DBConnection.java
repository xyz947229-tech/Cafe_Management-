import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔥 IMPORTANT: Change database name if needed
            String url = "jdbc:mysql://localhost:3306/cafe";
            String user = "root";
            String password = "shweta@2008"; 

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully");

            return con;

        } catch (Exception e) {
            System.out.println("Database Connection Error:");
            e.printStackTrace();
            return null;
        }
    }
}