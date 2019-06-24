package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shaplov
 * @since 11.06.2019
 */
public class SigninController extends HttpServlet {

    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (logic.isCredential(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            User user = logic.findByLogin(new User("", login));
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute("invalid", "Credential invalid");
            doGet(req, resp);
        }
    }
}
