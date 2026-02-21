
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionClass {

    public static void main(String[] args) {

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            // Step 1: Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Create Connection
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cafe_management",
                    "root",
                    "shweta@2008"
            );

            System.out.println("Database Connected Successfully!");

            // Step 3: Create Statement
            stm = con.createStatement();

            // Step 4: Execute Query
            rs = stm.executeQuery("SELECT * FROM items");

            // Step 5: Process Result
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                System.out.println(id + " | " + name + " | " + price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (con != null) con.close();
                System.out.println("Connection Closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}