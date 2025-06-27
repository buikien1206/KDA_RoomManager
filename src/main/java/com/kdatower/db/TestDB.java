package com.kdatower.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT username,password FROM accounts"
             )) {
            while (rs.next()) {
                System.out.println(
                  "User=" + rs.getString("username") +
                  ", Pass=" + rs.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
