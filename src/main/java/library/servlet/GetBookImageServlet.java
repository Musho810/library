package library.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/getBookImage")
public class GetBookImageServlet extends HttpServlet {


    private static final String IMAGE_PATH = "C:\\Users\\Mush\\IdeaProjects\\myLibrary2023\\projectimages\\";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookPic = req.getParameter("bookPic");

        String filePath = IMAGE_PATH + bookPic;


        File imageFiles = new File(filePath);

        if (imageFiles.exists()) {
            try (FileInputStream inputStream = new FileInputStream(imageFiles)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFiles.length());
                OutputStream outputStream = resp.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}