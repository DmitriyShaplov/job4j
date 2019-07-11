package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shaplov
 * @since 11.06.2019
 */
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (session.getAttribute("login") == null) {
            if (!req.getRequestURI().contains("/list") || req.getRequestURI().contains("/edit")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", req.getContextPath()));
            }
        } else {
            if (req.getRequestURI().contains("/signin") || req.getRequestURI().contains("/create")) {
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/list", req.getContextPath()));
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
