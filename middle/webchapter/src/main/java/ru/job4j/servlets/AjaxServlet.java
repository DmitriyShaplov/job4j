package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shaplov
 * @since 18.06.2019
 */
public class AjaxServlet extends HttpServlet {

    private final Map<Integer, UserModel> users = new ConcurrentHashMap<>();
    private final AtomicInteger index = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<UserModel> resultList = new ArrayList<>(users.values());
        String jsonString = mapper.writeValueAsString(resultList);
        pw.append(jsonString);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        UserModel model = mapper.readValue(sb.toString(), UserModel.class);
        users.put(index.getAndIncrement(), model);
    }

    /**
     * Stored model.
     */
    private static class UserModel {
        private String name;
        private String surname;
        private String sex;
        private String description;

        public UserModel(String name, String surname, String sex, String description) {
            this.name = name;
            this.surname = surname;
            this.sex = sex;
            this.description = description;
        }

        public UserModel() {
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getSex() {
            return sex;
        }

        public String getDescription() {
            return description;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
