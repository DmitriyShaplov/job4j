package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for dynamic cities update.
 *
 * @author shaplov
 * @since 24.06.2019
 */
public class CitiesController extends HttpServlet {

    private final DbLocations db = DbLocations.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        String country = req.getParameter("country");
        PrintWriter pw = resp.getWriter();
        List<String> cities = db.getCities(country);
        StringBuilder sb = new StringBuilder("[");
        for (String city : cities) {
            sb.append("{\"title\":\"");
            sb.append(city);
            sb.append("\"}, ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        pw.append(sb.toString());
        pw.flush();
    }
}
