package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import connection.DatabaseEnvVar;

public class DatabaseTest {
    @Test
    void testDatabseConncetion() {

        DatabaseEnvVar env = new DatabaseEnvVar();
        String DATABASE_URL = env.getEnvVariable("DATABASE_URL");
        String DATABASE_NAME = env.getEnvVariable("DATABASE_NAME");

        String DATABASE_USER_NAME = env.getEnvVariable("DATABASE_USER_NAME");
        String DATABASE_USER_PASSWORD = env.getEnvVariable("DATABASE_USER_PASSWORD");
        String JDBC_Driver = env.getEnvVariable("JDBC_DRIVER");
        Connection conn = null;
        try {
            System.out.println("Connecting to database...");
            System.out.println("URL: " + DATABASE_URL);
            System.out.println("Driver: " + JDBC_Driver);

            try {
                Class.forName(JDBC_Driver);
                conn = DriverManager.getConnection(
                        DATABASE_URL + DATABASE_NAME, DATABASE_USER_NAME, DATABASE_USER_PASSWORD);

                System.out.println("Connection Established Successfully");
                conn = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USER_NAME,
                        DATABASE_USER_PASSWORD);
                Assert.assertNotNull(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    Assert.assertTrue(conn.isClosed());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
