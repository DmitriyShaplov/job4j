package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.InvalidPassword;
import ru.job4j.servlets.exceptions.RepeatedLoginException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            logic.add(new User(null, name, login, email, password, Role.USER));
        } catch (RepeatedLoginException e) {
            req.setAttribute("error", "Such login is already exists");
            doGet(req, resp);
            return;
        } catch (InvalidPassword e) {
            req.setAttribute("error", "Invalid password");
            doGet(req, resp);
            return;
        }
        if (logic.isCredential(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("id", logic.findByLogin(new User("", login)).getId());
            session.setAttribute("role", Role.USER);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            doGet(req, resp);
        }
    }
}
