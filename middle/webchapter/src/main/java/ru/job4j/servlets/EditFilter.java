package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shaplov
 * @since 12.06.2019
 */
public class EditFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        if ("".equals(id)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
            return;
        }
        Role role = (Role) session.getAttribute("role");
        String curId = (String) session.getAttribute("id");
        User user = ValidateService.getInstance().findById(new User(id));
        if (role.getPriority() <= user.getPriority() && !curId.equals(user.getId())) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
