package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.RepeatedLoginException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shaplov
 * @since 06.06.2019
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        req.setAttribute("error", false);
        try {
            logic.add(new User(name, login, email));
        } catch (RepeatedLoginException e) {
            req.setAttribute("error", true);
        }
        resp.sendRedirect(String.format("%s/create", req.getContextPath()));
    }
}
