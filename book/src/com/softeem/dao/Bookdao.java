package com.softeem.dao;

import com.softeem.bean.Book;
import com.softeem.utils.BaseDaoInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface Bookdao extends BaseDaoInterface<Book> {
    public Integer queryForPageTotalCount() throws SQLException;
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;

    public List<Book> queryByPrice(int begin, int pageSize,int min, int max) throws SQLException;
    public Integer queryByPriceTotal(int min, int max) throws SQLException;

    public Integer queryForPageTotalCount(String name, String author,int min,int max) throws SQLException;
    public List<Book> queryForPageItems(int begin, int pageSize, String name, String author, int min, int max) throws SQLException;


}