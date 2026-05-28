package migration.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        System.out.println("Database Connection Test");
        System.out.println();

        // Test Source Database
        testConnection(
            "Source (E00197SV0203)",
            "jdbc:oracle:thin:@E00197SV0203:1521:ORCL",
            "SZH_SMS",
            "SZH_SMS"
        );

        System.out.println();

        // Test Target Database
        testConnection(
            "Target (E00736SV0001)",
            "jdbc:oracle:thin:@E00736SV0001:1521:ORCL",
            "SZH_SMS",
            "SZH_SMS"
        );

        System.out.println();
        System.out.println("Test completed!");
    }

    private static void testConnection(String name, String url, String username, String password) {
        System.out.println("Testing: " + name);
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("[OK] Driver loaded");
            
            // Connect
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("[OK] Connection established");
            
            // Test query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT SYSDATE FROM DUAL");
            
            if (rs.next()) {
                String sysdate = rs.getString(1);
                System.out.println("[OK] Query executed: SYSDATE = " + sysdate);
            }
            
            System.out.println("[SUCCESS] " + name + " is working!");
            
        } catch (Exception e) {
            System.out.println("[FAILED] Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
