package com.alpha.ioc.domain;

import com.alpha.ioc.di.annotation.Component;
import com.alpha.ioc.di.annotation.Inject;

import java.util.List;

@Component
public class BookService {
    @Inject
    private BookRepository bookRepository;
    public List<Book> queryByAuthor(String author) {
        List<Book> books = bookRepository.getBooksByAuthor(author);
        return books;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
