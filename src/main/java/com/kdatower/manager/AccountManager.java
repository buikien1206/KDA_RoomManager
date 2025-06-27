package com.kdatower.manager;

import com.kdatower.dao.AccountDAO;

public class AccountManager {
    private final AccountDAO dao = new AccountDAO();

    public boolean login(String u, String p) {
        return dao.login(u, p);
    }

    /** 
     * Trả về true nếu chèn được tài khoản mới,
     * false nếu username đã tồn tại (INSERT OR IGNORE chèn 0 dòng)
     */
    public boolean register(String u, String p) {
        int inserted = dao.add(u, p);
        return inserted > 0;
    }
}
