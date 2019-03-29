package com.alpha.ioc.cmdapp;

import com.alpha.ioc.domain.BookService;
import com.alpha.ioc.persist.mongodb.BookRepositoryImp;
import com.alpha.ioc.persist.rdb.BookRepsitoryImp;
import com.alpha.ioc.persist.rdb.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {
    @Bean("bookService")
    @Profile("rdb")
    BookService byRdb() {
        DataSource datasouce = new DataSource();
        datasouce.setDbConfig("db.properties");
        BookRepsitoryImp repsitoryImp = new BookRepsitoryImp();
        repsitoryImp.setDataSource(datasouce);
        BookService bookService = new BookService();
        bookService.setBookRepository(repsitoryImp);
        return bookService;
    }

    @Bean("bookService")
    @Profile("mongodb")
    BookService byMongodb() {
        com.alpha.ioc.persist.mongodb.DataSource datasouce = new com.alpha.ioc.persist.mongodb.DataSource("ioc");
        BookRepositoryImp repsitoryImp = new BookRepositoryImp();
        repsitoryImp.setDataSource(datasouce);
        BookService bookService = new BookService();
        bookService.setBookRepository(repsitoryImp);
        return bookService;
    }

}
