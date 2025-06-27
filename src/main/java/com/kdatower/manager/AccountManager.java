package com.kdatower.manager;

import com.kdatower.model.Account;
import com.kdatower.dao.AccountXML;
import java.util.*;

public class AccountManager {
    private List<Account> accounts;

    public AccountManager() {
        accounts = AccountXML.readAccounts();
    }

    public boolean login(String username, String password) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(Account acc) {
        for (Account a : accounts) {
            if (a.getUsername().equals(acc.getUsername())) return false;
        }
        accounts.add(acc);
        AccountXML.writeAccounts(accounts);
        return true;
    }
}

