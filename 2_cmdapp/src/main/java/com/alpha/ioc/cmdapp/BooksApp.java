package com.alpha.ioc.cmdapp;


import com.alpha.ioc.di.IocContainer;
import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookService;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class BooksApp {
    public static void main(String[] args) throws URISyntaxException {
        IocContainer iocContainer = new IocContainer();
        URL root = BooksApp.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(root);

        iocContainer.initialize(new File(root.toURI()));
        BookService bookService = (BookService) iocContainer.getComponent(BookService.class);

        List<Book> books = bookService.queryByAuthor(args[0]);

        books.forEach(book -> System.out.println(book.getTitle() + "   " + book.getPress()));
    }
}
