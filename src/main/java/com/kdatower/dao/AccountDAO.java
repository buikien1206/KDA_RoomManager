package com.kdatower.dao;

import com.kdatower.db.ConnectionFactory;
import java.sql.*;

public class AccountDAO {
    /** Kiểm tra login */
    public boolean login(String username, String password) {
        String sql = "SELECT 1 FROM accounts WHERE username=? AND password=?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Chèn tài khoản mới, in log và trả về số dòng đã chèn */
    public int add(String username, String password) {
        System.out.println("Inserting user=" + username);
        String sql = "INSERT OR IGNORE INTO accounts(username,password) VALUES(?,?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            int updated = ps.executeUpdate();
            System.out.println("  -> rows inserted: " + updated);
            return updated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
