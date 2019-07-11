package ru.job4j.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.job4j.model.Seat;
import ru.job4j.model.User;
import ru.job4j.service.Validate;
import ru.job4j.service.ValidateService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author shaplov
 * @since 25.06.2019
 */
public class HallServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        List<Seat> seats = logic.getSeats();
        Map<Integer, Set<Seat>> seatMap = new TreeMap<>();
        for (Seat seat : seats) {
            int row = seat.getRow();
            if (!seatMap.containsKey(row) || seatMap.get(row) == null) {
                seatMap.put(row, new TreeSet<>(Set.of(seat)));
            } else {
                seatMap.get(row).add(seat);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayJson = objectMapper.createArrayNode();
        for (var entry : seatMap.entrySet()) {
            ObjectNode node = objectMapper.createObjectNode();
            ArrayNode arrayNode = objectMapper.createArrayNode();
            node.put("row", entry.getKey());
            for (var entry2 : entry.getValue()) {
                ObjectNode innerNode = objectMapper.createObjectNode();
                innerNode.put("place", entry2.getPlace());
                innerNode.put("price", entry2.getPrice());
                innerNode.put("sold", entry2.isSold());
                arrayNode.add(innerNode);
            }
            node.set("seats", arrayNode);
            arrayJson.add(node);
        }
        String json = objectMapper.writeValueAsString(arrayJson);
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(sb.toString());
        int row = node.get("row").asInt();
        int place = node.get("place").asInt();
        int price = node.get("price").asInt();
        String username = node.get("username").asText();
        String phone = node.get("phone").asText();
        Seat seat = new Seat(row, place, price, false);
        User user = new User(username, phone, null);
        ObjectNode resNode = mapper.createObjectNode();
        try {
            logic.makePayment(seat, user);
            resNode.put("success", true).put("row", row).put("place", place);
        } catch (Exception e) {
            resNode.put("success", false).put("row", row).put("place", place);
        }
        PrintWriter pw = resp.getWriter();
        String json = mapper.writeValueAsString(resNode);
        pw.append(json);
        pw.flush();
    }
}
