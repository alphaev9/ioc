package com.alpha.ioc.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService;

    @Before
    public void setUp() {
        ArrayList<Book> list = new ArrayList<>();
        list.add(new Book("alpha", "spring cloud", "pivot"));
        list.add(new Book("alpha", "hibernate", "crc"));
        list.add(new Book("apple", "C# technology", "microsoft"));
        BookRepository bookRepository = author -> list.stream()
                .filter(book -> book.getAuthor() == author)
                .collect(Collectors.toList());
        bookService = new BookService();
        bookService.setBookRepository(bookRepository);
    }

    @Test
    public void queryByAuthor() {
        List<Book> books = bookService.queryByAuthor("alpha");
        books.forEach(book -> assertEquals("alpha", book.getAuthor()));
    }
}