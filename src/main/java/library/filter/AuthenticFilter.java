package library.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/homePage", "/books", "/authors"})
public class AuthenticFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("active") == null) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {

    }
}
