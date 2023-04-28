package library.servlet;

import library.manager.BookManager;
import library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookManager.getAll();
        req.setAttribute("books", bookList);
        req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchedName = req.getParameter("searchedName");
        List<Book> bookList = bookManager.getAllBYSearchedName(searchedName);
        req.setAttribute("books", bookList);
        req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req,resp);
    }
}
