package com.softeem.service.impl;

import com.softeem.bean.Manager;
import com.softeem.dao.ManagerDao;
import com.softeem.dao.impl.ManagerDaoImpl;
import com.softeem.service.ManagerService;

import java.sql.SQLException;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public Manager login(Manager manager) throws SQLException {
        return managerDao.queryUserByManagernameAndPassword(manager.getMname(), manager.getPassword());
    }
}
