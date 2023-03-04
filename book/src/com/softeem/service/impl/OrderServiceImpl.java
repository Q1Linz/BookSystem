package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.bean.CartItem;
import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.dao.Bookdao;
import com.softeem.dao.OrderDao;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.Bookdaoimpl;
import com.softeem.dao.impl.OrderDaoImpl;
import com.softeem.dao.impl.OrderItemDaoImpl;
import com.softeem.service.Cart;
import com.softeem.service.OrderService;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    //订单dao
    private OrderDao orderDao = new OrderDaoImpl();
    //订单项dao
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    //图书dao
    private Bookdao bookdao = new Bookdaoimpl();

    /**
     *
     * @param cart
     * @param userId
     * @return
     * @throws SQLException
     */
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        String orderId = "" + System.currentTimeMillis() +userId;
        Order order = new Order();
        order.setOrderId(orderId);//生成订单编号
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));//生成创建时间
        order.setPrice(cart.getTotalPrice());//生成总价格
        order.setStatus(0);//订单状态设置 0：未发货 1：已发货 2：已签收
        order.setUserId(userId);//设置用户编号，知道订单是哪个用户下的单
        orderDao.save(order);
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            OrderItem item = new OrderItem();
            item.setName(entry.getValue().getName());//设置订单项名字
            item.setCount(entry.getValue().getCount());//设置订单项的数量
            item.setPrice(entry.getValue().getTotalPrice());//设置订单项的价格
            item.setTotalPrice(entry.getValue().getTotalPrice());//设置订单项总价
            item.setOrderId(orderId);//设置订单项外键id订单编号
            orderItemDao.save(item);
            //更新库存和销量
            Book book = bookdao.findById(entry.getValue().getId());//通过图书的id返回图书对象
            book.setSales(book.getSales() + entry.getValue().getCount());//设置销量
            book.setStock(book.getStock() - entry.getValue().getCount());//设置库存
            bookdao.updateById(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException {
        Page<Order> page = new Page<>();
        int totalCount = orderDao.pageRecord();
        page.setPageTotal(totalCount);
        page.setPageTotal((totalCount + pageSize - 1)/pageSize);
        page.setPageNo(pageNo);
        page.setItems(orderDao.page(page.getPageNo()));
        return page;
    }
}