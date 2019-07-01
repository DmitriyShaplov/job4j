package ru.job4j.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for getting list of countries by ajax.
 *
 * @author shaplov
 * @since 24.06.2019
 */
public class CountriesController extends HttpServlet {

    private final DbLocations db = DbLocations.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode array = objectMapper.valueToTree(db.getCountries());
        pw.append(objectMapper.writeValueAsString(array));
        pw.flush();
    }
}
