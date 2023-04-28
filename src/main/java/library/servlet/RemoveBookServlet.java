package library.servlet;

import library.manager.BookManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/books/remove")
public class RemoveBookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        bookManager.removeBookById(bookId);
        resp.sendRedirect("/books");
    }

}
