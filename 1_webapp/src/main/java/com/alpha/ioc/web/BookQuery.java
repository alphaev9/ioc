package com.alpha.ioc.web;


import com.alpha.ioc.common.di.Component;
import com.alpha.ioc.di.annotation.Inject;
import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookRepository;
import com.alpha.ioc.domain.BookService;
import com.alpha.ioc.persist.mongodb.BookRepositoryImp;
import com.alpha.ioc.persist.mongodb.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookQuery", urlPatterns = "/query", initParams = {@WebInitParam(name = "db.config", value = "db.properties")})
@Component
public class BookQuery extends HttpServlet {
    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        /*String config = getInitParameter("db.config");

        DataSource dataSource = DataSourceFactory.buildDataSorceFromProperties(config);
        BookRepsitoryImp bookRepsitoryImp = new BookRepsitoryImp();
        bookRepsitoryImp.setDataSource(dataSource);
*/

        DataSource dataSource = new DataSource("ioc");
        BookRepository bookRepository = new BookRepositoryImp();
        ((BookRepositoryImp) bookRepository).setDataSource(dataSource);


        BookService bookService = new BookService();
        bookService.setBookRepository(bookRepository);
        List<Book> books = bookService.queryByAuthor(author);

        request.setAttribute("author", author);
        request.setAttribute("books", books);
        request.setAttribute("display", "show");
        List<String> heads = new ArrayList();
        heads.add("Title");
        heads.add("Press");
        request.setAttribute("heads", heads);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}

