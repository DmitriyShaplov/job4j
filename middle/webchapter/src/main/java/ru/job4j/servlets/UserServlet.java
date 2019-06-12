package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author shaplov
 * @since 04.06.2019
 */
public class UserServlet extends HttpServlet {


    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    private final Map<String, Function<User, String>> actions = new HashMap<>();

    {
        actions.put("add", user -> {
            if (logic.add(user)) {
                return "user added";
            } else {
                return "something wrong";
            }
        });
        actions.put("update", user -> {
            if (logic.update(user)) {
                return "user updated";
            } else {
                return "something wrong";
            }
        });
        actions.put("delete", user -> {
            if (logic.delete(user)) {
                return "user deleted";
            } else {
                return "something wrong";
            }
        });
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        if (id == null) {
            writer.append(logic.findAll().toString());
        } else {
            writer.append(logic.findById(constructUser(req)).toString());
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (actions.containsKey(action)) {
            User user = constructUser(req);
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(actions.get(action).apply(user));
            writer.flush();
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private User constructUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        return new User(id, name, login, email, password, role);
    }
}
