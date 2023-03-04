package com.softeem.service;

import com.softeem.bean.Book;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    public void addBook(Book book) throws SQLException;

    public void deleteBookById(Integer id) throws SQLException;

    public void updateBook(Book book) throws SQLException;

    public Book queryBookById(Integer id) throws SQLException;

    public List<Book> queryBooks() throws SQLException;

    public Page<Book> page(int pageNo,int pageSize) throws SQLException;

    public Page<Book> queryBookByPrice(int min,int max,int pageNo, int pageSize) throws SQLException;

    public Page<Book> queryBookByPrice(int min,int max,int pageNo, int pageSize,String name,String author) throws SQLException;
}
