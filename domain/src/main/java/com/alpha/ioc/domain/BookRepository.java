package com.alpha.ioc.domain;

import java.util.List;

public interface BookRepository {
    List<Book> getBooksByAuthor(String author);
}
