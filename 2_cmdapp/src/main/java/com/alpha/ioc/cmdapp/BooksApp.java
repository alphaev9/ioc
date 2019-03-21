package com.alpha.ioc.cmdapp;


import com.alpha.ioc.di.IocContainer;
import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookRepository;
import com.alpha.ioc.domain.BookService;
import com.alpha.ioc.persist.mongodb.BookRepositoryImp;
import com.alpha.ioc.persist.mongodb.DataSource;
import com.alpha.ioc.persist.rdb.BookRepsitoryImp;
import com.alpha.ioc.persist.rdb.DataSourceFactory;

import java.util.List;

public class BooksApp {
    public static void main(String[] args) {
        /*IocContainer iocContainer=new IocContainer();
        iocContainer.initialize();

        DataSource dataSource = new DataSource("ioc");
        BookRepository bookRepository = new BookRepositoryImp();
        ((BookRepositoryImp) bookRepository).setDataSource(dataSource);


        BookService bookService = new BookService();
        bookService.setBookRepository(bookRepository);


        List<Book> books = bookService.queryByAuthor(args[0]);

        books.forEach(book -> System.out.println(book.getTitle() + "   " + book.getPress()));
    */}
}
