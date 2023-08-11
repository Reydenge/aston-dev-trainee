package ru.reydenge.simpleservlet.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBaseUtil {
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/spring_db";
            String jdbcUsername = "postgres";
            String jdbcPassword = "postgres";
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("Enable to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
}
