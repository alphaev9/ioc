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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("mongodb")
public class ServiceByMongodbTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testMongodbBookService() {
        List<Book> books = bookService.queryByAuthor("alpha");
        books.forEach(book -> assertEquals("alpha", book.getAuthor()));

    }
}