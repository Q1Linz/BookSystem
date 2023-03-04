package com.softeem.test;

import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class OrderItemDaoImplTest {
    @Test
    public void save(){
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        try {
            orderItemDao.save(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1659929959734"));
            orderItemDao.save(new OrderItem(null,"java", 2,new BigDecimal(100),new BigDecimal(100),"1659929959734"));
            orderItemDao.save(new OrderItem(null,"C", 1,new BigDecimal(100),new BigDecimal(100),"1659929959734"));
        } catch (SQLException throwables) {

        }
    }
}
