package com.alpha.ioc.persist.mongodb;

import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BookRepositoryImp implements BookRepository {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Book> getBooksByAuthor(String author) {
        MongoCollection<Document> collection = dataSource.getCollection("books");
        BasicDBObject filter = new BasicDBObject("book.author", author);
        FindIterable<Book> books = collection.find(filter, Book.class);
        List<Book> bookList = new ArrayList<>();
        books.forEach((Consumer<? super Book>) book -> bookList.add(book));
        return bookList;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
