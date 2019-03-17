package com.alpha.ioc.persist.rdb;

import com.alpha.ioc.di.annotation.Component;
import com.alpha.ioc.di.annotation.Inject;
import com.alpha.ioc.di.annotation.Profile;
import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
@Profile("rdb")
public class BookRepsitoryImp implements BookRepository {
    @Inject
    private DataSource dataSource;

    @Override
    public List<Book> getBooksByAuthor(String author) {
        Connection connection = dataSource.getConnection();
        try {
            String sql = "SELECT title,press FROM " + dataSource.getTableName() + " WHERE author=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                String title = rs.getString("title");
                String press = rs.getString("press");
                book.setTitle(title);
                book.setPress(press);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
