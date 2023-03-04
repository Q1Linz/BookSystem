package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserServiceImplTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() throws SQLException {
        userService.registerUser(new User(null, "小明", "098", "222qq@.com"));
    }

    @Test
    public void login() {

    }

    @Test
    public void existsUsername() {
    }
}