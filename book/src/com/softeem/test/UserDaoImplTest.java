package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void findAll() throws SQLException {
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void save() throws SQLException {
        User user = new User(null, "xxx", "123", "111@qq.com");
        userDao.save(user);
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }

    @Test
    public void queryUserByUsername() throws SQLException {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() throws SQLException {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin"));
    }
}