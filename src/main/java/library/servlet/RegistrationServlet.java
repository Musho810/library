package library.servlet;
import library.manager.UserManager;
import library.model.Role;
import library.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@WebServlet(urlPatterns = "/registration")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1 Mb
        maxFileSize = 1024 * 1024 * 10, // 10 Mb
        maxRequestSize = 1024 * 1024 * 100 // 100 Mb
)
public class RegistrationServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    private static final String IMAGE_PATH = "C:\\Users\\Mush\\IdeaProjects\\mylibrary23\\projectimages\\";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("useremail");
        if (userManager.getUserByEmail(email) != null) {
            req.setAttribute("msg", "User already exist");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            String username = req.getParameter("username");
            String usersurname = req.getParameter("usersurname");
            String useremail = req.getParameter("useremail");
            String userpassword = req.getParameter("userpassword");
            Part userPicPart = req.getPart("userPic");
            String fileName = null;
            if ( userPicPart.getSize()!=0 ) {
                long nanoTime = System.nanoTime();
                fileName = nanoTime + "_" + userPicPart.getSubmittedFileName();
                userPicPart.write(IMAGE_PATH + fileName);
            }
            Role role = Role.valueOf(req.getParameter("role"));
            User user = User.builder()
                    .name(username)
                    .surname(usersurname)
                    .email(useremail)
                    .password(userpassword)
                    .userPic(fileName)
                    .role(role)
                    .build();
            userManager.add(user);
            resp.sendRedirect("/login");
        }
    }
}