package com.alpha.ioc.cmdapp;


import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.alpha.ioc"})
public class BooksApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BooksApp.class);
        BookService bookService = context.getBean(BookService.class);

        List<Book> books = bookService.queryByAuthor(args[0]);

        books.forEach(book -> System.out.println(book.getTitle() + "   " + book.getPress()));
    }
}
