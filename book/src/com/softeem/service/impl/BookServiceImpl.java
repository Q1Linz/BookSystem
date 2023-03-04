package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.dao.Bookdao;
import com.softeem.dao.impl.Bookdaoimpl;
import com.softeem.service.BookService;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    Bookdao bookdao = new Bookdaoimpl();

    @Override
    public void addBook(Book book) throws SQLException {
        bookdao.save(book);
    }

    @Override
    public void deleteBookById(Integer id) throws SQLException {
        bookdao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        bookdao.updateById(book);
    }

    @Override
    public Book queryBookById(Integer id) throws SQLException {
        return bookdao.findById(id);
    }

    @Override
    public List<Book> queryBooks() throws SQLException {
        return bookdao.findAll();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) throws SQLException {
//       需要调这俩方法
//       public Integer queryForPageTotalCount() throws SQLException;
//       public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;
        Page<Book> page = new Page<>();
        Integer totalCount = bookdao.queryForPageTotalCount();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize -1) / pageSize);//总页数
        page.setPageNo(pageNo);//设置当前页
        page.setItems(bookdao.queryForPageItems((page.getPageNo() - 1) * pageSize, pageSize));//设置分页查询的结果集
        return page;
    }

    @Override
    public Page<Book> queryBookByPrice(int min, int max,int pageNo, int pageSize) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookdao.queryByPriceTotal(min,max);//获取总记录数
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        System.out.println("pageNo传进来是 " + pageNo);
        page.setPageNo(pageNo);
        System.out.println("service"+page.getPageNo());
        page.setItems(bookdao.queryByPrice((page.getPageNo() - 1) * pageSize, pageSize,min,max));
        return page;
    }

    @Override
    public Page<Book> queryBookByPrice(int min, int max, int pageNo, int pageSize, String name, String author) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookdao.queryForPageTotalCount(name,author,min,max);//获取总记录数
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount + pageSize -1) / pageSize);
        page.setPageNo(pageNo);
        page.setItems(bookdao.queryForPageItems((page.getPageNo() - 1) * pageSize, pageSize,name,author,min,max));
        return page;
    }

}
