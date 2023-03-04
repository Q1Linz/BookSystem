package com.softeem.dao;

import com.softeem.bean.Manager;
import com.softeem.bean.User;
import com.softeem.utils.BaseDaoInterface;

import java.sql.SQLException;

public interface ManagerDao extends BaseDaoInterface<Manager> {
    public Manager queryUserByManagernameAndPassword(String username, String password) throws SQLException;
}
