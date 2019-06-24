package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.InvalidPassword;
import ru.job4j.servlets.exceptions.RepeatedLoginException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shaplov
 * @since 12.06.2019
 */
public class RoleController extends HttpServlet {

    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String roleStr = req.getParameter("role");
        Role role = Role.valueOf(roleStr);
        logic.updateRole(new User(id, role));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
