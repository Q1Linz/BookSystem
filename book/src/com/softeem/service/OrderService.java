package com.softeem.service;

import com.softeem.bean.Order;
import com.softeem.utils.Page;

import java.sql.SQLException;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId) throws SQLException;

    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException;
}