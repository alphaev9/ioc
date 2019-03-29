package com.alpha.ioc.cmdapp;


import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BooksApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);

        List<Book> books = bookService.queryByAuthor(args[0]);

        books.forEach(book -> System.out.println(book.getTitle() + "   " + book.getPress()));
    }
}
