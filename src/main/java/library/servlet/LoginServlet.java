package library.servlet;
import library.manager.UserManager;
import library.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserManager userManager =new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String useremail = req.getParameter("useremail");
        String password = req.getParameter("password");
        User user = userManager.getUserByEmailAndPassword(useremail, password);
        if (user == null) {
            req.setAttribute("msg", "Email or password incorect");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("active",user);
            resp.sendRedirect("/homePage");
        }
    }
}
