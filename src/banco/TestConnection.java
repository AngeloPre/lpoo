package banco;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            if (conn != null) {
                System.out.println("========================================");
                System.out.println("Connection established successfully! ✅");
                System.out.println("Database URL: " + conn.getMetaData().getURL());
                System.out.println("Database User: " + conn.getMetaData().getUserName());
                System.out.println("========================================");
            } else {
                System.out.println("Failed to establish connection. Connection object is null.");
            }
            
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
            e.printStackTrace();
            
            if (e.getMessage().contains("db.properties")) {
                System.err.println("\n*** HINT: Make sure 'db.properties' file is in the correct directory (the root of your project). ***");
            }
            if (e.getMessage().contains("Connection refused")) {
                System.err.println("\n*** HINT: Is the PostgreSQL database server running on 'postgres:5432'? ***");
            }
        }
    }
}