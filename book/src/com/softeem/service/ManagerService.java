package com.softeem.service;

import com.softeem.bean.Manager;
import com.softeem.bean.User;

import java.sql.SQLException;

public interface ManagerService {
    /**
     * Manager Login
     * @param manager
     * @return
     * @throws SQLException
     */
    public Manager login(Manager manager) throws SQLException;
}
