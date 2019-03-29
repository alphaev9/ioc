package com.alpha.ioc.web;


import com.alpha.ioc.domain.Book;
import com.alpha.ioc.domain.BookService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookQuery", urlPatterns = "/query")
public class BookQuery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

        BookService bookService = (BookService) context.getBean("bookService");


        String author = request.getParameter("author");

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

