package com.alpha.ioc.domain;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;
    public List<Book> queryByAuthor(String author) {
        List<Book> books = bookRepository.getBooksByAuthor(author);
        return books;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
