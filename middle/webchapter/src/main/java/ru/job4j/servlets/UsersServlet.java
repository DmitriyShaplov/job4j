package ru.job4j.servlets;

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
public class UsersServlet extends HttpServlet {

    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logic.findAll());
        HttpSession session = req.getSession();
        User curUser = logic.findById(new User((String) session.getAttribute("id")));
        req.setAttribute("curId", curUser.getId());
        req.setAttribute("role", curUser.getRole().toString());
        req.setAttribute("priority", curUser.getPriority());
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        if (!id.isEmpty()) {
            HttpSession session = req.getSession();
            logic.delete(new User(id));
            if (session.getAttribute("id").equals(id)) {
                session.invalidate();
            }
        }
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
