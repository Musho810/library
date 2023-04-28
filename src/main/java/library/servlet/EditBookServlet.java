package library.servlet;

import library.manager.AuthorManager;
import library.manager.BookManager;
import library.model.Book;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1 Mb
        maxFileSize = 1024 * 1024 * 10, // 10 Mb
        maxRequestSize = 1024 * 1024 * 100 // 100 Mb
)
@WebServlet(urlPatterns = "/books/edit")
public class EditBookServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();
    private static final String IMAGE_PATH = "C:\\Users\\Mush\\IdeaProjects\\myLibrary2023\\projectimages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        Book book = bookManager.getById(bookId);
        req.setAttribute("book", book);
        req.setAttribute("authors", authorManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editBook.jsp").forward(req, resp);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
//        String bookPic = req.getParameter("bookPic");
        Part bookPicPart = req.getPart("bookPic");
        String fileName = null;


        if ( bookPicPart.getSize()!=0 ) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + bookPicPart.getSubmittedFileName();
            bookPicPart.write(IMAGE_PATH + fileName);
        }


        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getById(authorId))
                .bookPic(fileName)

                .build();
        bookManager.edit(book);
        resp.sendRedirect("/books");
    }
}

