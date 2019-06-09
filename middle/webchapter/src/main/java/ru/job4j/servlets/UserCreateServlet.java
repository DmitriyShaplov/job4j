package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.RepeatedLoginException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>CreateUser</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post'>"
                + "Name : <input type='text' name='name'/><br/>"
                + "Login : <input type='text' name='login'/><br/>"
                + "Email : <input type='text' name='email'/><br/>"
                + "<input type='submit'>"
                + "</form>"
                + "<br/>"
                + "<form action='" + req.getContextPath() + "/list' method='get'>"
                + "<input type='submit' value=\"User's list\"/></form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        try {
            logic.add(new User(name, login, email));
        } catch (RepeatedLoginException e) {
            resp.sendRedirect(String.format("%s/create.jsp?error=repeat", req.getContextPath()));
            return;
        }
        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
    }
}
