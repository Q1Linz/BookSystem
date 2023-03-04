package com.softeem.dao.impl;

import com.softeem.bean.Manager;
import com.softeem.bean.User;
import com.softeem.dao.ManagerDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class ManagerDaoImpl extends BaseDao implements ManagerDao {
    @Override
    public Manager queryUserByManagernameAndPassword(String username, String password) throws SQLException {
        return queryRunner.query("select * from t_manager where mname = ? and password = ?", new BeanHandler<>(Manager.class, getProcess()), username, password);
    }

    @Override
    public List<Manager> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Manager manager) throws SQLException {

    }

    @Override
    public void updateById(Manager manager) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Manager findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Manager> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public int pageRecord() throws SQLException {
        return 0;
    }

}
