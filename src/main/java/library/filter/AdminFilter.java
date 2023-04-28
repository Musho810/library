package library.filter;

import library.model.Role;
import library.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/authors/remove", "/books/remove", "/authors/edit", "/books/edit"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("active");
        if (user == null || user.getRole() != Role.ADMIN) {
            response.sendRedirect("/homePage");
        } else {
            filterChain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }
}
