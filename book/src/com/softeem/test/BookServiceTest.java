package com.softeem.test;

import com.softeem.bean.Book;
import com.softeem.service.BookService;
import com.softeem.service.impl.BookServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void page() throws SQLException {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println("book = " + book);
        }

    }

    @Test
    public void testQuery() throws SQLException {
        System.out.println(bookService.queryBookById(1));
    }

}
