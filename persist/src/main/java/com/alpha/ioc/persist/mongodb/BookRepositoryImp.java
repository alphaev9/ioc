package com.alpha.ioc.persist.mongodb;

import com.alpha.ioc.di.annotation.Component;
import com.alpha.ioc.di.annotation.Inject;
import com.alpha.ioc.di.annotation.Profile;
import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@Profile("mongodb")
public class BookRepositoryImp implements BookRepository {
    @Inject
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
