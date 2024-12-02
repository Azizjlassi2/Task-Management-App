package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseEnvVar env = new DatabaseEnvVar();
    private static Connection conn;

    static String DATABASE_URL = env.getEnvVariable("DATABASE_URL");
    static String DATABASE_NAME = env.getEnvVariable("DATABASE_NAME");
    static String DATABASE_USER_NAME = env.getEnvVariable("DATABASE_USER_NAME");
    static String DATABASE_USER_PASSWORD = env.getEnvVariable("DATABASE_USER_PASSWORD");

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        } else {
            try {
                // Debugging to check if variables are null
                if (DATABASE_URL == null || DATABASE_NAME == null ||
                        DATABASE_USER_NAME == null || DATABASE_USER_PASSWORD == null) {
                    throw new NullPointerException("One or more environment variables are null!");
                }

                System.out.println("Connecting to database...");

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        DATABASE_URL + DATABASE_NAME, DATABASE_USER_NAME, DATABASE_USER_PASSWORD);

                System.out.println("Connection Established Successfully");

            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found: \n" + e);
            } catch (SQLException e) {
                System.out.println("Connection Error: \n" + e);
            } catch (NullPointerException e) {
                System.out.println("Environment Variable Error: \n" + e.getMessage());
            }

            return conn;
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection Closed Successfully");
            } catch (SQLException e) {
                System.out.println("Error When Closing Connection! \n " + e.getMessage());
            }
        }
    }
}
