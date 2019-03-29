package com.alpha.ioc.cmdapp;

import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("rdb")
public class ServiceByRdbTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testRdbBookService() {
        List<Book> books = bookService.queryByAuthor("张");
        books.forEach(book -> assertEquals("张", book.getAuthor()));
    }

}