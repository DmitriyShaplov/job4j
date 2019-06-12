package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.RepeatedLoginException;
import ru.job4j.servlets.exceptions.InvalidPassword;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shaplov
 * @since 06.06.2019
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("user", logic.findById(new User(id)));
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            logic.update(new User(id, name, login, email, password));
        } catch (RepeatedLoginException e) {
            req.setAttribute("error", "Such login is already exists");
        } catch (InvalidPassword e) {
            req.setAttribute("error", "Invalid password");
        }
        doGet(req, resp);
    }
}

