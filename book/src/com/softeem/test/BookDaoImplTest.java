package com.softeem.test;

import com.softeem.bean.Book;
import com.softeem.dao.Bookdao;
import com.softeem.dao.impl.Bookdaoimpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class BookDaoImplTest {
    private Bookdao bookdao = new Bookdaoimpl();

    @Test
    public void page() throws SQLException {
        Integer integer = bookdao.queryForPageTotalCount();
        System.out.println("integer = " + integer);
    }

    @Test
    public void testQuery() throws SQLException {
        List<Book> books = bookdao.queryForPageItems(0, 5, null, null, 100, 10);
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }
}
