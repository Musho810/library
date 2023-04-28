package library.servlet;

import library.manager.AuthorManager;
import library.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/authors")
public class AuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authorList = authorManager.getAll();
        req.setAttribute("authors",authorList);
        req.getRequestDispatcher("/WEB-INF/authors.jsp").forward(req,resp);
    }


}
