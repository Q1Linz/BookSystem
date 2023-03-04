package com.softeem.dao.impl;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public List<User> findAll() throws SQLException {
        return queryRunner.query("select * from t_user", new BeanListHandler<>(User.class, getProcess()));
    }

    @Override
    public void save(User user) throws SQLException {
        Long id = queryRunner.insert("insert into t_user values (null, ?, ?, ?)",new ScalarHandler<Long>(),user.getUsername(), user.getPassword(), user.getEmail());
        user.setId(id.intValue());
    }

    @Override
    public void updateById(User user) throws SQLException {
        queryRunner.update("update t_user set username = ?, password = ?, email = ? where id = ?", user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_user where id = ?", id);
    }

    @Override
    public User findById(Integer id) throws SQLException {
        return queryRunner.query("select * from t_user where id = ?", new BeanHandler<>(User.class, getProcess()), id);
    }

    @Override
    public List<User> page(Integer pageNumber) throws SQLException {
        return queryRunner.query("select * from t_user limit ?, ?", new BeanListHandler<>(User.class, getProcess()), (pageNumber - 1) * pageSize, pageSize);
    }

    @Override
    public int pageRecord() throws SQLException {
        return queryRunner.query("select count(*) from t_user", new ScalarHandler<Long>()).intValue();
    }

    @Override
    public User queryUserByUsername(String username) throws SQLException {
        return queryRunner.query("select * from t_user where username = ?", new BeanHandler<>(User.class, getProcess()), username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws SQLException {
        return queryRunner.query("select * from t_user where username = ? and password = ?", new BeanHandler<>(User.class, getProcess()), username, password);
    }
}
