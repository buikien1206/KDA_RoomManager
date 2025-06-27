package com.kdatower.db;

import java.sql.*;

public class ConnectionFactory {
    // Dùng dấu “/” hoặc escape “\\” cho backslash
    private static final String URL =
        // Cách 1: forward‐slash
        "jdbc:sqlite:G:/Drive của tôi/Database/app.db";
    // Hoặc
    // Cách 2: backslash escape
    // "jdbc:sqlite:G:\\Drive của tôi\\Database\\app.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
              "CREATE TABLE IF NOT EXISTS accounts (" +
              " username TEXT PRIMARY KEY," +
              " password TEXT NOT NULL" +
              ");"
            );
            stmt.executeUpdate(
              "CREATE TABLE IF NOT EXISTS apartments (" +
              " id TEXT PRIMARY KEY," +
              " owner TEXT," +
              " area REAL," +
              " building TEXT," +
              " floor INTEGER," +
              " status TEXT," +
              " num_people INTEGER," +
              " date_in TEXT," +
              " account TEXT" +
              ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
